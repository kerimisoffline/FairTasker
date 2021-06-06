/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.fairTasker.gg.ui.job;

/*
Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
*/

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.widget.AppCompatEditText;

import java.util.ArrayList;
import java.util.List;

import app.fairTasker.gg.R;
import app.fairTasker.gg.activity.MainActivity;
import app.fairTasker.gg.data.DataBase;
import app.fairTasker.gg.model.Job;
import app.fairTasker.gg.model.Person;
import app.fairTasker.gg.ui.BaseFragment;
import app.fairTasker.gg.util.Config;
import app.fairTasker.gg.util.Data;
import butterknife.BindView;


public class NewJobFragment extends BaseFragment {


    public static NewJobFragment fragment;
    private ArrayAdapter<Job> adapter;
    private List<Job> jList = new ArrayList<Job>();

    @BindView(R.id.btn_add_job) Button btn_add_job;
    @BindView(R.id.btn_delete_job) Button btn_delete_job;
    @BindView(R.id.btn_save_jobs) Button btn_save_job;
    @BindView(R.id.edit_job_name) AppCompatEditText edt_name;
    @BindView(R.id.edit_job_hardness) AppCompatEditText edt_hardness;
    @BindView(R.id.list_job) ListView list_job;

    public NewJobFragment(){
        layoutId = R.layout.fragment_new_job;
        Data.selectedJobs = null;
    }


    @Override
    protected void viewRef(View view) {
    }

    @Override
    protected void handler() {
        fragment = this;
        final DataBase db = new DataBase(getContext());
        db.open();

        if(Data.selectedJobs==null) {
            List<Job> jobs = db.jobList();
            Data.selectedJobs = jobs;
            jList = jobs;
            adapter = new ArrayAdapter<Job>(getContext(), android.R.layout.simple_list_item_1, jobs);
            list_job.setAdapter(adapter);
        }

        btn_add_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name !=null && edt_name.getText() !=null && !edt_name.getText().toString().equals("") ? edt_name.getText().toString() : null;
                int hardness = edt_hardness !=null && edt_hardness.getText() !=null && !edt_hardness.getText().toString().equals("") ? Integer.valueOf(edt_hardness.getText().toString())  : null;
                Job job = null;
                if(name!=null) {
                    job = new Job(name, hardness);
                    db.addJob(job);
                    adapter.add(job);
                }
            }
        });

        btn_delete_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Job job = null;
                if(list_job!=null && list_job.getCount() > 0) {
                    job = (Job) list_job.getAdapter().getItem(0);
                    db.deleteJob(job);
                    adapter.remove(job);
                }
            }
        });

        btn_save_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data.selectedJobs!=null) {
                    Data.selectedJobs = jList;
                    if(Data.selectedPersons!=null && Data.selectedJobs!=null && Data.selectedPersons.size()==Data.selectedJobs.size()) {
                        MainActivity.Current.Navigate(R.id.navigation_schedule, null, null, null);
                    }
                    else
                        Config.AlertDialog(getContext(),"",getContext().getResources().getString(R.string.error_calculate));
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragment = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}