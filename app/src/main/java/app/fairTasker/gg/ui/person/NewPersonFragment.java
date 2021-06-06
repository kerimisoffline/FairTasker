/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.fairTasker.gg.ui.person;

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
import app.fairTasker.gg.model.Person;
import app.fairTasker.gg.ui.BaseFragment;
import app.fairTasker.gg.util.Data;
import butterknife.BindView;

public class NewPersonFragment extends BaseFragment {

    public static NewPersonFragment fragment;
    private ArrayAdapter<Person> adapter;
    private List<Person> pList = new ArrayList<Person>();

    @BindView(R.id.btn_add_person) Button btn_add_person;
    @BindView(R.id.btn_delete_person) Button btn_delete_person;
    @BindView(R.id.btn_save_list) Button btn_save_list;
    @BindView(R.id.edit_person_name)
    AppCompatEditText edt_name;
    @BindView(R.id.edit_person_surname) AppCompatEditText edt_surname;
    @BindView(R.id.list) ListView list;


    public NewPersonFragment(){
        layoutId = R.layout.fragment_new_person;
        Data.selectedPersons =null;
    }

    @Override
    protected void viewRef(View view) {
        super.viewRef(view);
    }

    @Override
    protected void handler() {
        fragment = this;

        final DataBase db = new DataBase(getContext());
        db.open();

        if(Data.selectedPersons==null) {
            List<Person> persons = db.personalList();
            Data.selectedPersons = persons;
            pList = persons;
            adapter = new ArrayAdapter<Person>(getContext(), android.R.layout.simple_list_item_1, persons);
            list.setAdapter(adapter);
        }

        btn_add_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name !=null && edt_name.getText() !=null && !edt_name.getText().toString().equals("") ? edt_name.getText().toString() : null;
                String surname = edt_surname !=null && edt_surname.getText() !=null && !edt_surname.getText().toString().equals("") ? edt_surname.getText().toString() : null;
                Person person = null;
                if(name!=null && surname!=null) {
                    person = new Person(name, surname);
                    db.addPerson(person);
                    adapter.add(person);
                }
            }
        });

        btn_delete_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = null;
                if(list!=null && list.getCount() > 0) {
                    person = (Person) list.getAdapter().getItem(0);
                    db.deletePerson(person);
                    adapter.remove(person);
                }
            }
        });

        btn_save_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.selectedPersons = pList;
                if(Data.selectedPersons!=null) {
                    MainActivity.Current.Navigate(R.id.navigation_new_job, null, null, null);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragment = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}