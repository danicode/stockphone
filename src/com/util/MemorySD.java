package com.util;

import android.os.Environment;

public class MemorySD {
	
	public final static String PATH_ROOT = Environment.getExternalStorageDirectory().getPath();
	public final static String STATE = Environment.getExternalStorageState();

	public static boolean memorySDMonted() {
		return (Environment.MEDIA_MOUNTED.equals(STATE));
	}
	
}
