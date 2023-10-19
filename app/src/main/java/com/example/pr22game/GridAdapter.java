package com.example.pr22game;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private Integer mCols, mRows;
    private static final int CELL_CLOSE = 0;
    private static final int CELL_OPEN = 1;
    private static final int CELL_DELETE = -1;

    private ArrayList<String> arrPict; // массив картинок
    private String PictureCollection; // Префикс набора картинок
    private Resources mRes; // Ресурсы приложени

    public GridAdapter(Context context, int cols, int rows) {
        mContext = context;
        mCols = cols;
        mRows = rows;

        arrPict = new ArrayList<String>();
        // Пока определяем префикс так, позже он будет браться из настроек
        PictureCollection = "animal";
        // Получаем все ресурсы приложения
        mRes = mContext.getResources();

        // Метод заполняющий массив vecPict
        makePictArray();
    }

    private void makePictArray () {
        // очищаем вектор
        arrPict.clear();
        // добавляем
        for (int i = 0; i < ((mCols * mRows) / 2); i++)
        {
            arrPict.add (PictureCollection + Integer.toString (i));
            arrPict.add (PictureCollection + Integer.toString (i));
        }
        // перемешиваем
        Collections.shuffle(arrPict);
    }

    @Override
    public int getCount() {
        return mCols * mRows;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView view; // для вывода картинки

        if (convertView == null)
            view = new ImageView(mContext);
        else
            view = (ImageView) convertView;

        // Получаем идентификатор ресурса для картинки,
        // которая находится в векторе vecPict на позиции position
        Integer drawableId = mRes.getIdentifier(arrPict.get(position), "drawable", mContext.getPackageName());

        //view.setImageResource(R.drawable.ic_launcher);
        view.setImageResource(drawableId);
        return view;

    }
}