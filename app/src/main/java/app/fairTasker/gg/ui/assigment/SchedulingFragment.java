/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.fairTasker.gg.ui.assigment;

import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import app.fairTasker.gg.R;
import app.fairTasker.gg.activity.MainActivity;
import app.fairTasker.gg.model.Job;
import app.fairTasker.gg.model.Person;
import app.fairTasker.gg.ui.BaseFragment;
import app.fairTasker.gg.util.Config;
import app.fairTasker.gg.util.Data;
import butterknife.BindView;


public class SchedulingFragment extends BaseFragment {

    public static SchedulingFragment Current;
    private ArrayAdapter<Spanned> adapter;
    public ArrayList<Spanned> schedule = new ArrayList<Spanned>();
    private Job[][] checkRepeatAssign;
    final int callcount = 60;

    @BindView(R.id.list_task) ListView list_task;
    public SchedulingFragment(){
        layoutId = R.layout.fragment_schedule;
    }

    @Override
    protected void viewRef(View view) {
        super.viewRef(view);
    }

    @Override
    protected void handler() {
        Current = this;

        if(Data.selectedPersons!=null && Data.selectedJobs!=null){
            boolean exitLoop = false;
            while(!exitLoop) {
                exitLoop = assignment(Data.selectedPersons, Data.selectedJobs);
            }
            adapter = new ArrayAdapter<Spanned>(getContext(), android.R.layout.simple_list_item_1, schedule);
            list_task.setAdapter(adapter);
        }
    }

    private boolean assignment(List<Person> persons, List<Job> jobs){

        Job[][] assignment = new Job[persons.size()][jobs.size()];
        checkRepeatAssign = new Job[persons.size()][jobs.size()];
        ArrayList<Job> arranger = new ArrayList<>();

/*
        ArrayList<Integer> hardnessList = new ArrayList<Integer>();
        for(Job j : jobs){
            try {
                hardnessList.add(Integer.parseInt(j.getHardness()));
            } catch (Exception e){
                //
            }
        }

 */

        for (int j = 0; j< jobs.size();j++) {
            List<Job> fJobs = new ArrayList<Job>(jobs);
            for (int i = 0; i < persons.size(); i++) {
                if(j==0) {
                    if (jobs.size() > 0) {
                        int ras;
                        int counter = 0;
                        do {
                            Random r = new Random();
                            ras = r.nextInt(fJobs.size());
                            counter++;
                            if (counter > callcount) {
                                return false;
                            }
                        } while (i > 0 && (assignment[i - 1][j].getHardness() - 1 == fJobs.get(ras).getHardness() || assignment[i - 1][j].getHardness() == fJobs.get(ras).getHardness() || assignment[i - 1][j].getHardness() + 1 == fJobs.get(ras).getHardness()));

                        arranger.add(fJobs.get(ras));
                        assignment[i][j] = fJobs.get(ras);
                        fJobs.remove(ras);
                    }
                } else {
                    assignment[i][j] = arranger.get(i);
                }
            }
            reArrange(arranger, persons.size()> 0 ? persons.size()-1 : 0);

        }

        for (int j = 0; j< jobs.size();j++) {
            String text = "<font color=#D10076>"+ (j+1) + " .inci iş günü </font>";
            schedule.add(Html.fromHtml(text));
            for (int i = 0; i < persons.size(); i++) {
                schedule.add(Html.fromHtml("<font color=#0000FF>" +  persons.get(i).toString() +"</font> <font color=#000000>"  +" -- " + assignment[i][j].toString()+"</font>"));
            }
            text = null;
        }
        return true;
    }

    private <T> void reArrange(List<T> list,int size){
        int from = 1;
        int to = 0;
        for(int i= 0;i<size;i++){
            Collections.swap(list, from, to);
            from++;
            to++;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Current = null;
        schedule = null;
        checkRepeatAssign = null;
        System.gc();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
