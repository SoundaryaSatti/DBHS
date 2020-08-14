package com.integro.dbhs.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.demono.AutoScrollViewPager;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.integro.dbhs.R;
import com.integro.dbhs.adapters.CoverPhotosAdapter;
import com.integro.dbhs.apis.ApiClients;
import com.integro.dbhs.apis.ApiServices;
import com.integro.dbhs.model.CoverPhotos;
import com.integro.dbhs.model.CoverPhotosList;
import com.integro.dbhs.model.Videos;
import com.integro.dbhs.model.VideosList;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {

    private AutoScrollViewPager viewPager;
    private ArrayList<CoverPhotos> coverPhotosArrayList;
    private CoverPhotosAdapter adapter;
    ArrayList<Videos> videosArrayList;
    Call<VideosList> videosListCall;

    private ApiServices apiServices;
    public static final String API_KEY = "AIzaSyDKr5dvZ9VzDcBcTn5kdM_0MOOAbq6uzJU";
    public static final String VIDEO_ID = "kzNdT3Jj3jk";
    public YouTubePlayerView playerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        playerView = view.findViewById(R.id.youtube_player);
        apiServices = ApiClients.getClients().create(ApiServices.class);
        videosArrayList=new ArrayList<>();

        viewPager = view.findViewById(R.id.vpCoverPhotos);
        coverPhotosArrayList = new ArrayList<>();
        getCoverPhotos();
       // getLifecycle().addObserver(playerView);
        //getVideos();

       /* ImageView ivCall = view.findViewById(R.id.ivCall);
        ImageView ivEmail = view.findViewById(R.id.ivEmail);

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDial();
            }
        });


        ivEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });*/
        return view;
    }

    public void getDial() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final CharSequence[] phone = new CharSequence[]{"0832 2422854"};
        final CharSequence[] optionsChosen = phone;
        builder.setItems(optionsChosen, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (optionsChosen[item].equals("0832 2422854")) {
                    String phone = "0832 2422854";
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    startActivity(callIntent);
                    startActivity(callIntent);
                }
            }
        });
        builder.show();
    }

    public void sendEmail() {
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:?subject=" + " " + "&body=" + " " + "&to=" + "dboscopanjim@yahoo.co.in");
        testIntent.setData(data);
        startActivity(testIntent);
    }


    public void getCoverPhotos() {
        String date = "2020-02-26 23:57:19";
        Call<CoverPhotosList> coverPhotosListCall = ApiClients.getClients().create(ApiServices.class).getCoverPhotosList(date);
        coverPhotosListCall.enqueue(new Callback<CoverPhotosList>() {
            @Override
            public void onResponse(Call<CoverPhotosList> call, Response<CoverPhotosList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body().getCoverPhotosArrayList() == null) {
                    return;
                }
                int size = response.body().getCoverPhotosArrayList().size();
                for (int i = 0; i < size; i++) {
                    coverPhotosArrayList.add(response.body().getCoverPhotosArrayList().get(i));
                    adapter = new CoverPhotosAdapter(getContext(), coverPhotosArrayList);
                    viewPager.setAdapter(adapter);
                    viewPager.startAutoScroll();
                }
            }

            @Override
            public void onFailure(Call<CoverPhotosList> call, Throwable t) {

            }
        });
    }

    private void getVideos() {
        String date = "2020-07-22 21:46:23";
        videosListCall = apiServices.getVideos(date);
        videosListCall.enqueue(new Callback<VideosList>() {
            @Override
            public void onResponse(Call<VideosList> call, Response<VideosList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        int size = response.body().getVideos().size();
                        if (size > 0) {
                            videosArrayList.addAll(response.body().getVideos());
                            playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                                @Override
                                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                    String videoId  = videosArrayList.get(0).getVideoId();
                                    if (!videoId.isEmpty()) {
                                        Log.d("VIDEO_ID", videoId);
                                        youTubePlayer.cueVideo("https://youtu.be/"+videoId, 0);
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), "Failed to load videos", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<VideosList> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });

    }
}
