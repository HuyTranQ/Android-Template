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

    private static Context context;
    private static Resource resource;

    private Resource() {
    }

    public static Resource from(Context activityContext) {
        if (context == null) {
            context = activityContext.getApplicationContext();
            resource = new Resource();
        }
        return resource;
    }

    public int getResourceId(String name ,
                             Type type) {
        String packageName = context.getPackageName();
        return context.getResources().getIdentifier(name , type.value() , packageName);
    }

    public int getColorId(String name) {
        int result = -1;
        try {
            result = R.color.class.getField(name).getInt(null);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public String getString(String name) {
        return context.getResources().getString(getResourceId(name, Type.STRING));
    }

    public String getString(int id) {
        return context.getResources().getString(id);
    }

    public int getInteger(String name) {
        return context.getResources().getInteger(getResourceId(name, Type.INTEGER));
    }

    public int getInteger(int id) {
        return context.getResources().getInteger(id);
    }

    public String[] getStringArray(String name) {
        return context.getResources().getStringArray(getResourceId(name, Type.STRING_ARRAY));
    }

    public String[] getStringArray(int id) {
        return context.getResources().getStringArray(id);
    }

    public int[] getIntegerArray(String name) {
        return context.getResources().getIntArray(getResourceId(name, Type.INTEGER_ARRAY));
    }

    public int[] getIntegerArray(int id) {
        return context.getResources().getIntArray(id);
    }

    public boolean getBoolean(String name) {
        return context.getResources().getBoolean(getResourceId(name, Type.BOOLEAN));
    }

    public boolean getBoolean(int id) {
        return context.getResources().getBoolean(id);
    }

    public int getColor(int id) {
        return ContextCompat.getColor(context, id);
    }

    public int getColor(String name) {
        return ContextCompat.getColor(context , getColorId(name));
    }

    public TypedArray getTypedArray(int id) {
        return context.getResources().obtainTypedArray(id);
    }

    public TypedArray getTypedArray(String name) {
        return context.getResources().obtainTypedArray(getResourceId(name , Type.TYPED_ARRAY));
    }

    public float getDimension(String name) {
        return context.getResources().getDimension(getResourceId(name, Type.DIMEN));
    }

    public float getDimension(int id) {
        return context.getResources().getDimension(id);
    }

    public Drawable getDrawable(int id) {
        return ContextCompat.getDrawable(context, id);
    }

    public Drawable getDrawable(String name) {
        return ContextCompat.getDrawable(context , getResourceId(name , Type.DRAWABLE));
    }
}