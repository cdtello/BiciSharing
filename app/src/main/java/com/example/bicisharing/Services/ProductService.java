package com.example.bicisharing.Services;

import android.database.Cursor;
import android.util.Log;

import com.example.bicisharing.Entities.Event;
import com.example.bicisharing.Entities.Food;
import com.example.bicisharing.Entities.Product;

import java.util.ArrayList;

public class ProductService {
    public ArrayList<Product> cursorToArrayListProduct(Cursor cursor){
        ArrayList<Product> list = new ArrayList<>();
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                Product product = new Product(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(4),
                        cursor.getString(3)
                );
                list.add(product);
            }
        }
        return list;
    }

    public ArrayList<Event> cursorToArrayListEvents(Cursor cursor){
        ArrayList<Event> list = new ArrayList<>();
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                Event event = new Event(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                list.add(event);
            }
        }
        return list;
    }

    public ArrayList<Food> cursorToArrayListFood(Cursor cursor){
        ArrayList<Food> list = new ArrayList<>();
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                Food food = new Food(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(4),
                        cursor.getString(3)
                );
                list.add(food);
            }
        }
        return list;
    }
}
