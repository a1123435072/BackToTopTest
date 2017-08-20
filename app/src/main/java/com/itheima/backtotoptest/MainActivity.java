package com.itheima.backtotoptest;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private FloatingActionButton fab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		List<String> datas=new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			datas.add("条目"+i);
		}
		recyclerView.setAdapter(new MyAdapter(datas));
		fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				recyclerView.scrollToPosition(0);
				hideFAB();
			}
		});
	}

	private void hideFAB() {
		fab.postDelayed(new Runnable() {
			@Override
			public void run() {
				AnimatorUtil.scaleHide(fab, new ViewPropertyAnimatorListener() {
					@Override
					public void onAnimationStart(View view) {

					}

					@Override
					public void onAnimationEnd(View view) {
						fab.setVisibility(View.GONE);
					}

					@Override
					public void onAnimationCancel(View view) {

					}
				});
			}
		},500);
	}
}
