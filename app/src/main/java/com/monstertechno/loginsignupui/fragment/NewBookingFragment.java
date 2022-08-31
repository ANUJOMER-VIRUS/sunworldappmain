package com.monstertechno.loginsignupui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.Retrofit.RetrofitAPI;
import com.monstertechno.loginsignupui.Retrofit.RetrofitClient;
import com.monstertechno.loginsignupui.modal.category;
import com.monstertechno.loginsignupui.modal.categorysubnames;
import com.monstertechno.loginsignupui.modal.getCategory;
import com.monstertechno.loginsignupui.modal.subcategoryData;
import com.monstertechno.loginsignupui.modal.subcategorylist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewBookingFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
   String pos;
long categorypos;


    String subcategoryName;
    Spinner spinner,spinner2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_newbooking,container,false);

       spinner=(Spinner) v.findViewById(R.id.categotyspinner);
       getcatname();
        spinner2=(Spinner) v.findViewById(R.id.subcategorySpinner);

        return  v;
    }

private void getsubcatname(String categoryid){
    String    subc;
        ArrayList<String> stringArrayList=new ArrayList<>();
        RetrofitAPI retrofitAPI=RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        subcategoryData subcategoryData=new subcategoryData(Integer.parseInt(categoryid));
        Call<subcategoryData> call=retrofitAPI.getsubcategory(subcategoryData);
        call.enqueue(new Callback<com.monstertechno.loginsignupui.modal.subcategoryData>() {
            @Override
            public void onResponse(Call<com.monstertechno.loginsignupui.modal.subcategoryData> call, Response<com.monstertechno.loginsignupui.modal.subcategoryData> response) {

                if(response.body().getStatus()){
                    ArrayList<subcategorylist> subcategoryData1=response.body().getResponseDataSubCategory().getSubcategorylists();
                    for (int i=0;i<subcategoryData1.size();i++){
                        subcategorylist subcategorylist=subcategoryData1.get(i);
                        stringArrayList.add(subcategorylist.getSubcategory_title());
                    }



                }
                setSpinnerss(stringArrayList);
            }

            @Override
            public void onFailure(Call<com.monstertechno.loginsignupui.modal.subcategoryData> call, Throwable t) {

            }
        });

}

    private void setSpinnerss(ArrayList<String> stringArrayList) {
        spinner2.setAdapter(new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item ,stringArrayList));
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String subc2;

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subc2=adapterView.getItemAtPosition(i).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private  void getcatname(){
        ArrayList<String> stringArrayList=new ArrayList<>();
        ArrayList<String>stringArrayList1=new ArrayList<>();
        RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<getCategory> call=retrofitAPI.getcategory();
        call.enqueue(new Callback<getCategory>() {
            @Override
            public void onResponse(Call<getCategory> call, Response<getCategory> response) {
                if (response.body().isStatus()) {
                    ArrayList<category> categoryArrayList = response.body().getResponseDataCategory().getCategoryArrayList();
                    for (int i = 0; i < categoryArrayList.size(); i++
                    ) {
                        category category = categoryArrayList.get(i);

stringArrayList1.add(category.getId());
                        stringArrayList.add(category.getCategoryTitle());
                    }
                }
                setSpinnercs(stringArrayList,stringArrayList1);

            }
            @Override
            public void onFailure(Call<getCategory> call, Throwable t) {

            }
        });

    }

    private void setSpinnercs(ArrayList<String> arrayList, ArrayList<String> stringArrayList1) {
        spinner.setAdapter(new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item ,arrayList));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categorypos=adapterView.getItemIdAtPosition(i);
                String categorynam=adapterView.getItemAtPosition(i).toString();

                String position=stringArrayList1.get((int)categorypos);
                pos=(position);
                getsubcatname(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}