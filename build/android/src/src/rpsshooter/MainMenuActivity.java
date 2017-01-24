
/*
 * This file is part of Jkop
 * Copyright (c) 2016-2017 Job and Esther Technologies, Inc.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package rpsshooter;

public class MainMenuActivity extends android.app.Activity
{
	private motion.BackendForAndroidView backend = null;

	public void onCreate(android.os.Bundle savedInstance) {
		super.onCreate(savedInstance);
		cave.GuiApplicationContextForAndroid ctx = cave.GuiApplicationContextForAndroid.forActivityContext((android.content.Context)this);
		backend = motion.BackendForAndroidView.forScene((motion.Scene)new rpsshooter.MainMenu(), (cape.AndroidApplicationContext)ctx);
		setContentView(backend.getAndroidView());
	}

	public void onResume() {
		super.onResume();
		getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
		android.app.ActionBar ab = getActionBar();
		ab.hide();
	}

	public void onStart() {
		super.onStart();
		backend.onStart();
	}

	public void onStop() {
		super.onStop();
		backend.onStop();
	}

	public void onDestroy() {
		super.onDestroy();
		backend.onDestroy();
		backend = null;
	}
}
