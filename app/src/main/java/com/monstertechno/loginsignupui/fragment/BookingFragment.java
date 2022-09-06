package com.monstertechno.loginsignupui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.monstertechno.loginsignupui.Adapter.BookingAdapter;
import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.Retrofit.RetrofitAPI;
import com.monstertechno.loginsignupui.Retrofit.RetrofitClient;
import com.monstertechno.loginsignupui.modal.Allbooking;
import com.monstertechno.loginsignupui.modal.Allbookingres;
import com.monstertechno.loginsignupui.modal.name;
import com.monstertechno.loginsignupui.modal.statusbooking;
import com.monstertechno.loginsignupui.modal.statusbookingdata;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingFragment extends Fragment {
    RecyclerView recyclerView;
    List<Allbooking>allbookingList;
    TextView book;
    String userid;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingFragment newInstance(String param1, String param2) {
        BookingFragment fragment = new BookingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_booking, container, false);
        TabLayout tabLayout=view.findViewById(R.id.tab_layout);
        sharedPreferences =this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        userid=sharedPreferences.getString("userid","notfound");
        allbookingList =new ArrayList<>();
      recyclerView=view.findViewById(R.id.bookingrv);
     book =view.findViewById(R.id.nobooking);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      Allbookingres allbookingres=new Allbookingres(userid);
        BookingAdapter bookingAdapter=new BookingAdapter(allbookingList);
        recyclerView.setAdapter(bookingAdapter);

        RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);

        Call<Allbookingres> allbookingresCall=retrofitAPI.getallbooking(allbookingres);
        allbookingresCall.enqueue(new Callback<Allbookingres>() {
            @Override
            public void onResponse(Call<Allbookingres> call, Response<Allbookingres> response) {
                if(response.body().getStatus()){
                    allbookingList.addAll(response.body().getResponseData().getAllBookingList());
                    bookingAdapter.notifyDataSetChanged();
                }
                else {
                    book.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onFailure(Call<Allbookingres> call, Throwable t) {
                book.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                book.setText("server fail");
            }
        });




        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);

                    Call<Allbookingres> allbookingresCall=retrofitAPI.getallbooking(allbookingres);
                    allbookingresCall.enqueue(new Callback<Allbookingres>() {
                        @Override
                        public void onResponse(Call<Allbookingres> call, Response<Allbookingres> response) {
                            if(response.body().getStatus()){
                                allbookingList.clear();
                                allbookingList.addAll(response.body().getResponseData().getAllBookingList());
                                bookingAdapter.notifyDataSetChanged();
                                book.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            else {
                                book.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.INVISIBLE);
                                book.setText("server fail");
                            }
                        }

                        @Override
                        public void onFailure(Call<Allbookingres> call, Throwable t) {
                            book.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.INVISIBLE);
                        }
                    });


                } else if (tab.getPosition() == 1) {



                    RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
                    statusbookingdata statusbookingdata=new statusbookingdata(userid,"in progress");
                    Call<statusbooking> statusbookingCall=retrofitAPI.getstatusbook(statusbookingdata);
                    statusbookingCall.enqueue(new Callback<statusbooking>() {
                        @Override
                        public void onResponse(Call<statusbooking> call, Response<statusbooking> response) {
                            if(response.body().getStatus()){
                                allbookingList.clear();
                                allbookingList.addAll(response.body().getResponseData().getBookingList());
                                bookingAdapter.notifyDataSetChanged();
                                book.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            else {
                                book.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.INVISIBLE);
                                book.setText("NO Booking In progress");
                            }
                        }

                        @Override
                        public void onFailure(Call<statusbooking> call, Throwable t) {
                            book.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.INVISIBLE);
                            book.setText("server fail");
                        }
                    });





                } else if (tab.getPosition() == 2) {
                    RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
                    statusbookingdata statusbookingdata=new statusbookingdata(userid,"approved");
                    Call<statusbooking> statusbookingCall=retrofitAPI.getstatusbook(statusbookingdata);
                    statusbookingCall.enqueue(new Callback<statusbooking>() {
                        @Override
                        public void onResponse(Call<statusbooking> call, Response<statusbooking> response) {
                            if(response.body().getStatus()){
                            allbookingList.clear();
allbookingList.addAll(response.body().getResponseData().getBookingList());
bookingAdapter.notifyDataSetChanged();
                                book.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            else {
                                book.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.INVISIBLE);
                                book.setText("NO Booking Approved");
                            }
                        }

                        @Override
                        public void onFailure(Call<statusbooking> call, Throwable t) {
                            book.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.INVISIBLE);
                            book.setText("server fail");
                        }
                    });




                }  else if (tab.getPosition() == 3) {
                    RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
                    statusbookingdata statusbookingdata=new statusbookingdata(userid,"rejected");
                    Call<statusbooking> statusbookingCall=retrofitAPI.getstatusbook(statusbookingdata);
                    statusbookingCall.enqueue(new Callback<statusbooking>() {
                        @Override
                        public void onResponse(Call<statusbooking> call, Response<statusbooking> response) {
                            if(response.body().getStatus()){
                                allbookingList.clear();
                                allbookingList.addAll(response.body().getResponseData().getBookingList());
                                bookingAdapter.notifyDataSetChanged();
                                book.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            else {
                                book.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.INVISIBLE);
                                book.setText("NO Booking Rejected");
                            }
                        }

                        @Override
                        public void onFailure(Call<statusbooking> call, Throwable t) {
                            book.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.INVISIBLE);
                            book.setText("server fail");
                        }
                    });


                }
            }

                @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
        }

    }
