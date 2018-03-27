package com.yto.template.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;

import java.util.Timer;
import java.util.TimerTask;

public class DialogUtil {
	private ProgressDialog sProgressDialog;
	private AlertDialog alertDialog;
	private Timer sTimer;
	public static final int THRESHOLD_TIME = 100;

	/**
	 * 通用提示
	 */
	public void inform(Context ctx, String msg) {
		inform(ctx, "", msg);
	}

	/**
	 * 通用提示
	 */
	public void inform(Context ctx, String msg, OnClickListener listener) {
//		if(alertDialog!=null&&alertDialog.isShowing()){
//			alertDialog.dismiss();
//		}
//		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
//		builder.setCancelable(false);
//		builder.setTitle("友情提示");
//		builder.setIcon(null);
//		builder.setMessage(msg);
//		builder.setPositiveButton("我知道了", listener);
//		alertDialog = builder.create();
//		try {
//			alertDialog.show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		inform(ctx,"友情提示",msg,"我知道了",listener);
	}

	/**
	 * 通用提示
	 */
	public void inform(Context ctx, String title, String msg) {
		inform(ctx,title,msg,null);
	}
	/**
	 * 通用提示
	 */
	public void inform(Context ctx, String title, String msg, OnClickListener listener) {
//		if(alertDialog!=null&&alertDialog.isShowing()){
//			alertDialog.dismiss();
//		}
//		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
//		builder.setCancelable(false);
//		builder.setTitle(title);
//		builder.setIcon(null);
////		builder.setIcon(android.R.drawable.ic_dialog_alert);
//		builder.setMessage(msg);
//		builder.setPositiveButton("好的", listener);
//		alertDialog = builder.create();
//		try {
//			alertDialog.show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		inform(ctx,title,msg,"",listener);
	}

	/**
	 * 通用提示
	 */
	public void inform(Context ctx, String title, String msg,String okText, OnClickListener listener) {
		if(alertDialog!=null&&alertDialog.isShowing()){
			alertDialog.dismiss();
		}
		if(TextUtils.isEmpty(okText)){
			okText = "我知道了";
		}
		if(TextUtils.isEmpty(title)){
			title = "提示";
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setCancelable(false);
		builder.setTitle(title);
		builder.setIcon(null);
//		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage(msg);
		builder.setPositiveButton(okText, listener);
		alertDialog = builder.create();
		try {
			alertDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inform(Context ctx, String msg, Boolean b,
							  OnClickListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setCancelable(b);
		builder.setTitle("提示");
//		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage(msg);
		builder.setIcon(null);
		builder.setPositiveButton("确定", listener);
		try {
			builder.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 有两个按钮的通用提示
	 */
	public void inform(Context ctx, String title, String msg,
							  OnClickListener listener, OnClickListener listener1) {
//		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
//		builder.setCancelable(false);
//		builder.setTitle(title);
//		builder.setMessage(msg);
//		builder.setIcon(null);
//		builder.setPositiveButton("确定", listener);
//		builder.setNegativeButton("取消", listener1);
//		try {
//			builder.show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		inform(ctx,title,msg,null,listener,listener1);
	}

	/**
	 * 有两个按钮的通用提示
	 */
	public void inform(Context ctx, String msg,
							  OnClickListener listener, OnClickListener listener1) {
//		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
//		builder.setCancelable(false);
//		builder.setTitle("友情提示");
////		builder.setIcon(android.R.drawable.ic_dialog_alert);
//		builder.setMessage(msg);
//		builder.setIcon(null);
//		builder.setPositiveButton("确定", listener);
//		builder.setNegativeButton("取消", listener1);
//		try {
//			builder.show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		inform(ctx,"温馨提示",msg,null,null,listener,listener1);
	}
	/**
	 * 有两个按钮的通用提示
	 */
	public void inform(Context ctx, String title,String msg,String ok,
							  OnClickListener listener, OnClickListener listener1) {
//		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
//		builder.setCancelable(false);
//		builder.setTitle(title);
////		builder.setIcon(android.R.drawable.ic_dialog_alert);
//		builder.setMessage(msg);
//		builder.setIcon(null);
//		builder.setPositiveButton(ok, listener);
//		builder.setNegativeButton("取消", listener1);
//		try {
//			builder.show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		inform(ctx,title,msg,ok,null,listener,listener1);
	}

	public void showProgressDialog(Context context, int msgId) {
		showProgressDialog(context, context.getString(msgId));
	}

	public void showProgressDialog(Context context, String msg) {
		showProgressDialog(context, msg, -1);
	}

	public void showProgressDialog(Context context, int msgId,
										  long maxWaitTime) {
		showProgressDialog(context, context.getString(msgId), maxWaitTime);
	}

	public void setProgressMsg(String msg) {
		if (sProgressDialog != null) {
			sProgressDialog.setMessage(msg);
		}
	}

	public void showProgressDialog(Context context, String msg,
										  long maxWaitTime) {
		if (isProgressDialogShowing()) {
			setProgressMsg(msg);
		} else {
			try {

				sProgressDialog = ProgressDialog.show(context, "提示", msg, false, false);

				if (maxWaitTime > THRESHOLD_TIME) {
					sTimer = new Timer();
					sTimer.schedule(new TimerTask() {
						@Override
						public void run() {
							closeProgressDialog();
						}
					}, maxWaitTime);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * 有两个按钮的通用提示
	 */
	public void inform(Context ctx, String title, String msg,String okstr,String cancle,
							  OnClickListener listener, OnClickListener listener1) {
		if(alertDialog!=null&&alertDialog.isShowing()){
			alertDialog.dismiss();
		}
		if(TextUtils.isEmpty(okstr)){
			okstr = "确定";
		}
		if(TextUtils.isEmpty(cancle)){
			cancle = "取消";
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setCancelable(false);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setIcon(null);
		builder.setPositiveButton(okstr, listener);
		builder.setNegativeButton(cancle, listener1);
		alertDialog = builder.create();
		try {
			alertDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeProgressDialog() {
		try {
			if (sProgressDialog != null) {
				sProgressDialog.dismiss();
				sProgressDialog = null;
			}

			if (sTimer != null) {
				sTimer.cancel();
				sTimer = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean isProgressDialogShowing() {
		return (sProgressDialog != null && sProgressDialog.isShowing());
	}
	public void closeDialog() {
		try {
			if (alertDialog != null) {
				alertDialog.dismiss();
				alertDialog = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean isDialogShowing() {
		return (alertDialog != null && alertDialog.isShowing());
	}
}
