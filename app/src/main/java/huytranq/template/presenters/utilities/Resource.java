package huytranq.template.presenters.utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.lang.reflect.Field;

import huytranq.template.R;

/**
 * Created by huytr on 11-01-2016.
 */
public final class Resource {

    public enum Type {
        BOOLEAN("bool") ,
        COLOR("color") ,
        DIMEN("dimen") ,
        ID("item") ,
        INTEGER("integer") ,
        INTEGER_ARRAY("integer-array") ,
        TYPED_ARRAY("array") ,
        STRING("string") ,
        STRING_ARRAY("string-array") ,
        DRAWABLE("drawable")
        ;

        private final String name;

        private Type(String name) {
            this.name = name;
        }

        public boolean equals(Type type) {
            return name.equals(type.name);
        }

        public boolean equals(String name) {
            return this.name.equals(name);
        }

        public String value() {
            return name;
        }
    }

    public static int getResourceId(Context context ,
                                    String name ,
                                    Type type) {
        String packageName = context.getPackageName();
        return context.getResources().getIdentifier(name , type.value() , packageName);
    }

    public static int getColorId(String name) {
        int result = -1;
        try {
            result = R.color.class.getField(name).getInt(null);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public static String getString(Context context ,
                                   String name) {
        return context.getResources().getString(getResourceId(context, name, Type.STRING));
    }

    public static String getString(Context context ,
                                   int id) {
        return context.getResources().getString(id);
    }

    public static int getInteger(Context context ,
                                 String name) {
        return context.getResources().getInteger(getResourceId(context, name, Type.INTEGER));
    }

    public static int getInteger(Context context ,
                                 int id) {
        return context.getResources().getInteger(id);
    }

    public static String[] getStringArray(Context context ,
                                          String name) {
        return context.getResources().getStringArray(getResourceId(context, name, Type.STRING_ARRAY));
    }

    public static String[] getStringArray(Context context ,
                                          int id) {
        return context.getResources().getStringArray(id);
    }

    public static int[] getIntegerArray(Context context ,
                                        String name) {
        return context.getResources().getIntArray(getResourceId(context, name, Type.INTEGER_ARRAY));
    }

    public static int[] getIntegerArray(Context context ,
                                        int id) {
        return context.getResources().getIntArray(id);
    }

    public static boolean getBoolean(Context context ,
                                     String name) {
        return context.getResources().getBoolean(getResourceId(context, name, Type.BOOLEAN));
    }

    public static boolean getBoolean(Context context ,
                                     int id) {
        return context.getResources().getBoolean(id);
    }

    public static int getColor(Context context ,
                               int id) {
        return ContextCompat.getColor(context, id);
    }

    public static int getColor(Context context ,
                               String name) {
        return ContextCompat.getColor(context , getColorId(name));
    }

    public static TypedArray getTypedArray(Context context ,
                                           int id) {
        return context.getResources().obtainTypedArray(id);
    }

    public static TypedArray getTypedArray(Context context ,
                                           String name) {
        return context.getResources().obtainTypedArray(getResourceId(context , name , Type.TYPED_ARRAY));
    }

    public static float getDimension(Context context ,
                                     String name) {
        return context.getResources().getDimension(getResourceId(context, name, Type.DIMEN));
    }

    public static float getDimension(Context context ,
                                     int id) {
        return context.getResources().getDimension(id);
    }

    public static Drawable getDrawable(Context context ,
                                       int id) {
        return ContextCompat.getDrawable(context, id);
    }

    public static Drawable getDrawable(Context context ,
                                       String name) {
        return ContextCompat.getDrawable(context , getResourceId(context , name , Type.DRAWABLE));
    }
}
