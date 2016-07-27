package com.tp.venus.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.annotation.RawRes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtil {

	private final static int MAX_HEIGHT = 640;

	// decodes image and scales it to reduce memory consumption
	public static Bitmap decodeFile(File f, int size) {
		FileInputStream stream1 = null;
		FileInputStream stream2 = null;
		try {
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			stream1 = new FileInputStream(f);
			BitmapFactory.decodeStream(stream1, null, o);

			int degree = readPictureDegree(f.getPath());
			// int degree = 90;
			// Find the correct scale value. It should be the power of 2.
			// final int REQUIRED_SIZE = 200;
			// int width_tmp = o.outWidth, height_tmp = o.outHeight;
			double scale = 1;

			int length = 0;

			if (o.outWidth > o.outHeight) {
				length = o.outWidth;
			} else {
				length = o.outHeight;
			}

			if (size > MAX_HEIGHT) {
				size = MAX_HEIGHT;
			}

			while (true) {
				// if (width_tmp / 4 * 3 < size || height_tmp / 4 * 3 < size)
				// break;
				if (length / 5 * 4 < size)
					break;
				length = length / 5 * 4;
				scale = scale * 5 / 4;
			}

			// decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = (int) scale;
			o2.inJustDecodeBounds = false;
			stream2 = new FileInputStream(f);

			// Bitmap bitmap = BitmapFactory.decodeStream(stream2, null, o2);

			Bitmap bitmap = rotateBitmap(
					BitmapFactory.decodeStream(stream2, null, o2), degree);

			bitmap = decodeFile(bitmap, size, 1);

			return bitmap;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stream1 != null) {
					stream1.close();
				}
				if (stream2 != null) {
					stream2.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// decodes image and scales it to reduce memory consumption
	public static Bitmap decodeFile(Bitmap f, int size, int width) {
		try {
			// int degree = 90;
			// Find the correct scale value. It should be the power of 2.
			// final int REQUIRED_SIZE = 200;
			// int width_tmp = o.outWidth, height_tmp = o.outHeight;
			float scale = 1;

			int length = 0;

			if (f.getWidth() > f.getHeight()) {
				length = f.getWidth();
			} else {
				length = f.getHeight();
			}

			if (size > MAX_HEIGHT) {
				size = MAX_HEIGHT;
			}

			while (true) {
				// if (width_tmp / 4 * 3 < size || height_tmp / 4 * 3 < size)
				// break;
				if (length / 5 * 4 < size)
					break;
				length = length / 5 * 4;
				scale = scale * 5 / 4;
			}

			float s = (float) length / (float) (f.getWidth());

			Matrix matrix = new Matrix();
			matrix.postScale(s, s);
			// decode with inSampleSize
			f = Bitmap.createBitmap(f, 0, 0, f.getWidth(), f.getHeight(),
					matrix, true);

			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public static int readPictureDegree(String path) {
		int digree = 0;
		ExifInterface exif = null;
		try {
			exif = new ExifInterface(path);
		} catch (IOException e) {
			e.printStackTrace();
			exif = null;
		}
		if (exif != null) {
			// ��ȡͼƬ���������Ϣ
			int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_UNDEFINED);
			// ������ת�Ƕ�
			switch (ori) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				digree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				digree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				digree = 270;
				break;
			default:
				digree = 0;
				break;
			}
		}
		return digree;
	}


	public static Bitmap rotateBitmap(Bitmap bitmap, int degrees) {
		if (degrees == 0 || null == bitmap) {
			return bitmap;
		}
		Matrix matrix = new Matrix();
		matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
		Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		if (null != bitmap) {
			bitmap.recycle();
			bitmap = null;
		}
		return bmp;
	}


	public static void saveLocalBitmap(Bitmap bitmap, File file) {
		if (file.exists()) {
			file.delete();
		}

		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fOut);
			fOut.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fOut != null) {
					fOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Bitmap ReadBitMap(Context context, @RawRes int resId){

		BitmapFactory.Options opt = new BitmapFactory.Options();

		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		//获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is,null,opt);

	}

}
