package com.hoolai.qzone;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.UIManager;

public class Global {

	public static final String QZ_PLATFORM = "qzone";
	
	public static final int SANGO_APP_ID = 613;
	
	private static final Executor executor = Executors.newSingleThreadExecutor();
	
	private static final List<LoginUser> successLoginUsers = new ArrayList<LoginUser>();
	
	/** 单独的一个线程池用于执行异步的任务 */
	public static void execute(Runnable task){
		executor.execute(task);
	}
	
	/** 使用本机系统外观（如果有一个）的 LookAndFeel 类的名称设置当前的默认外观 */
	public static void initLookAndFeel(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { }
	}

	public static void addSuccessLogin(LoginUser loginUser) {
		successLoginUsers.add(loginUser);
	}
	
	public static void removeSuccessLogin(LoginUser loginUser){
		successLoginUsers.remove(loginUser);
	}
	
}
