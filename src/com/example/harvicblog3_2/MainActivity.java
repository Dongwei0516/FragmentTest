package com.example.harvicblog3_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		View.OnClickListener {
	private int stackID1, stackID2, stackID3, stackID4;
	private OnBackStackChangedListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnAddFrag1 = (Button) findViewById(R.id.btn_add_frag1);
		Button btnAddFrag2 = (Button) findViewById(R.id.btn_add_frag2);
		Button btnAddFrag3 = (Button) findViewById(R.id.btn_add_frag3);
		Button btnAddFrag4 = (Button) findViewById(R.id.btn_add_frag4);
		Button btnPopBackStack = (Button) findViewById(R.id.btn_pop_back_stack);
		Button btnBackToFrag2_0 = (Button) findViewById(R.id.btn_back_to_frg2_0);
		Button btnBackToFrag2_Includesive = (Button) findViewById(R.id.btn_back_to_frg2_inclusive);

		btnAddFrag1.setOnClickListener(this);
		btnAddFrag2.setOnClickListener(this);
		btnAddFrag3.setOnClickListener(this);
		btnAddFrag4.setOnClickListener(this);
		btnPopBackStack.setOnClickListener(this);
		btnBackToFrag2_0.setOnClickListener(this);
		btnBackToFrag2_Includesive.setOnClickListener(this);

		FragmentManager manager = getSupportFragmentManager();
		listener = new FragmentManager.OnBackStackChangedListener() {

			@Override
			public void onBackStackChanged() {
				// TODO Auto-generated method stub
				Log.d("qijian", "backstack changed");
			}
		};
		manager.addOnBackStackChangedListener(listener);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		FragmentManager manager = getSupportFragmentManager();
		manager.removeOnBackStackChangedListener(listener);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.btn_add_frag1: {
			Fragment1 fragment1 = new Fragment1();
			stackID1 = addFragment(fragment1, "fragment1");
		}
			break;
		case R.id.btn_add_frag2: {
			Fragment2 fragment2 = new Fragment2();
			stackID2 = addFragment(fragment2, "fragment2");
		}
			break;
		case R.id.btn_add_frag3: {
			Fragment3 fragment3 = new Fragment3();
			stackID3 = addFragment(fragment3, "fragment3");
		}
			break;
		case R.id.btn_add_frag4: {
			Fragment4 fragment4 = new Fragment4();
			stackID4 = addFragment(fragment4, "fragment4");
		}
			break;
		case R.id.btn_pop_back_stack: {
			popBackStack();
		}
			break;
		case R.id.btn_back_to_frg2_0: {
			popBackStackToFrag2_0();
		}
			break;
		case R.id.btn_back_to_frg2_inclusive: {
			popBackStackToFrag2_Inclusive();
		}
		}
	}

	private int addFragment(Fragment fragment, String stackName) {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.fragment_container, fragment);
		transaction.addToBackStack(stackName);
		return transaction.commit();
	}

	private void popBackStack() {
		FragmentManager manager = getSupportFragmentManager();
		manager.popBackStack();
	}

	private void popBackStackToFrag2_0() {
		FragmentManager manager = getSupportFragmentManager();
		manager.popBackStack("fragment2", 0);// 方法一,通过TAG回退
		// manager.popBackStack(stackID2, 0);//方法二,通过Transaction ID回退
	}

	private void popBackStackToFrag2_Inclusive() {
		FragmentManager manager = getSupportFragmentManager();
		// manager.popBackStack("fragment2",
		// FragmentManager.POP_BACK_STACK_INCLUSIVE);//方法一,通过TAG回退
		manager.popBackStack(stackID2, FragmentManager.POP_BACK_STACK_INCLUSIVE);// 方法二,通过Transaction
																					// ID回退
	}
}