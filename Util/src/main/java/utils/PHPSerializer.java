package utils;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class UnSerializeResult {
    public Object value;
    public int hv;

    public UnSerializeResult() {
    }

    public UnSerializeResult(Object value, int hv) {
        this.value = value;
        this.hv = hv;
    }
}

public class PHPSerializer {
    private static Package[] __packages = Package.getPackages();
    private static final byte __Quote = 34;
    private static final byte __0 = 48;
    private static final byte __1 = 49;
    private static final byte __Colon = 58;
    private static final byte __Semicolon = 59;
    private static final byte __C = 67;
    private static final byte __N = 78;
    private static final byte __O = 79;
    private static final byte __R = 82;
    private static final byte __U = 85;
    private static final byte __Slash = 92;
    private static final byte __a = 97;
    private static final byte __b = 98;
    private static final byte __d = 100;
    private static final byte __i = 105;
    private static final byte __r = 114;
    private static final byte __s = 115;
    private static final byte __LeftB = 123;
    private static final byte __RightB = 125;
    private static final String __NAN = new String("NAN");
    private static final String __INF = new String("INF");
    private static final String __NINF = new String("-INF");

    private PHPSerializer() {
    }

    public static byte[] serialize(Object obj) {
        return serialize(obj, "UTF-8");
    }

    public static byte[] serialize(Object obj, String charset) {
        HashMap ht = new HashMap();
        int hv = 1;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        hv = serialize(stream, obj, ht, hv, charset);
        byte[] result = stream.toByteArray();

        try {
            stream.close();
        } catch (Exception e) {
        }
        return result;
    }

    public static int serialize(ByteArrayOutputStream stream, Object obj,
                                HashMap ht, int hv, String charset) {
        if (obj == null) {
            hv++;
            writeNull(stream);
        } else {
            if (obj instanceof Boolean) {
                hv++;
                writeBoolean(stream, ((Boolean) obj).booleanValue() ? __1 : __0);
            } else if ((obj instanceof Byte) || (obj instanceof Short)
                    || (obj instanceof Integer)) {
                hv++;
                writeInteger(stream, getBytes(obj));
            } else if (obj instanceof Long) {
                hv++;
                writeDouble(stream, getBytes(obj));
            } else if (obj instanceof Float) {
                hv++;
                Float f = (Float) obj;

                if (f.isNaN()) {
                    writeDouble(stream, getBytes(__NAN));
                } else if (f.isInfinite()) {
                    if (f.floatValue() > 0) {
                        writeDouble(stream, getBytes(__INF));
                    } else {
                        writeDouble(stream, getBytes(__NINF));
                    }
                } else {
                    writeDouble(stream, getBytes(f));
                }
            } else if (obj instanceof Double) {
                hv++;
                Double d = (Double) obj;

                if (d.isNaN()) {
                    writeDouble(stream, getBytes(__NAN));
                } else if (d.isInfinite()) {
                    if (d.doubleValue() > 0) {
                        writeDouble(stream, getBytes(__INF));
                    } else {
                        writeDouble(stream, getBytes(__NINF));
                    }
                } else {
                    writeDouble(stream, getBytes(d));
                }
            } else if ((obj instanceof Character) || (obj instanceof String)) {
                hv++;
                writeString(stream, getBytes(obj, charset));
            } else if (obj.getClass().isArray()) {
                if (ht.containsKey(new Integer(obj.hashCode()))) {
                    writePointRef(stream,
                            getBytes(ht.get(new Integer(obj.hashCode()))));
                } else {
                    ht.put(new Integer(obj.hashCode()), new Integer(hv++));
                    hv = writeArray(stream, obj, ht, hv, charset);
                }
            } else if (obj instanceof ArrayList) {
                if (ht.containsKey(new Integer(obj.hashCode()))) {
                    writePointRef(stream,
                            getBytes(ht.get(new Integer(obj.hashCode()))));
                } else {
                    ht.put(new Integer(obj.hashCode()), new Integer(hv++));
                    hv = writeArrayList(stream, (ArrayList) obj, ht, hv,
                            charset);
                }
            } else if (obj instanceof HashMap) {
                if (ht.containsKey(new Integer(obj.hashCode()))) {
                    writePointRef(stream,
                            getBytes(ht.get(new Integer(obj.hashCode()))));
                } else {
                    ht.put(new Integer(obj.hashCode()), new Integer(hv++));
                    hv = writeHashMap(stream, (HashMap) obj, ht, hv, charset);
                }
            } else {
                if (ht.containsKey(new Integer(obj.hashCode()))) {
                    hv++;
                    writeRef(stream,
                            getBytes(ht.get(new Integer(obj.hashCode()))));
                } else {
                    ht.put(new Integer(obj.hashCode()), new Integer(hv++));
                    hv = writeObject(stream, obj, ht, hv, charset);
                }
            }
        }
        return hv;
    }

    private static void writeNull(ByteArrayOutputStream stream) {
        stream.write(__N);
        stream.write(__Semicolon);
    }

    private static void writeRef(ByteArrayOutputStream stream, byte[] r) {
        stream.write(__r);
        stream.write(__Colon);
        stream.write(r, 0, r.length);
        stream.write(__Semicolon);
    }

    private static void writePointRef(ByteArrayOutputStream stream, byte[] p) {
        stream.write(__R);
        stream.write(__Colon);
        stream.write(p, 0, p.length);
        stream.write(__Semicolon);
    }

    private static void writeBoolean(ByteArrayOutputStream stream, byte b) {
        stream.write(__b);
        stream.write(__Colon);
        stream.write(b);
        stream.write(__Semicolon);
    }

    private static void writeInteger(ByteArrayOutputStream stream, byte[] i) {
        stream.write(__i);
        stream.write(__Colon);
        stream.write(i, 0, i.length);
        stream.write(__Semicolon);
    }

    private static void writeDouble(ByteArrayOutputStream stream, byte[] d) {
        stream.write(__d);
        stream.write(__Colon);
        stream.write(d, 0, d.length);
        stream.write(__Semicolon);
    }

    private static void writeString(ByteArrayOutputStream stream, byte[] s) {
        byte[] slen = getBytes(new Integer(s.length));

        stream.write(__s);
        stream.write(__Colon);
        stream.write(slen, 0, slen.length);
        stream.write(__Colon);
        stream.write(__Quote);
        stream.write(s, 0, s.length);
        stream.write(__Quote);
        stream.write(__Semicolon);
    }

    private static int writeArray(ByteArrayOutputStream stream, Object a,
                                  HashMap ht, int hv, String charset) {
        int len = Array.getLength(a);
        byte[] alen = getBytes(new Integer(len));

        stream.write(__a);
        stream.write(__Colon);
        stream.write(alen, 0, alen.length);
        stream.write(__Colon);
        stream.write(__LeftB);
        for (int i = 0; i < len; i++) {
            writeInteger(stream, getBytes(new Integer(i)));
            hv = serialize(stream, Array.get(a, i), ht, hv, charset);
        }
        stream.write(__RightB);
        return hv;
    }

    private static int writeArrayList(ByteArrayOutputStream stream,
                                      ArrayList a, HashMap ht, int hv, String charset) {
        int len = a.size();
        byte[] alen = getBytes(new Integer(len));

        stream.write(__a);
        stream.write(__Colon);
        stream.write(alen, 0, alen.length);
        stream.write(__Colon);
        stream.write(__LeftB);
        for (int i = 0; i < len; i++) {
            writeInteger(stream, getBytes(new Integer(i)));
            hv = serialize(stream, a.get(i), ht, hv, charset);
        }
        stream.write(__RightB);
        return hv;
    }

    private static int writeHashMap(ByteArrayOutputStream stream, HashMap h,
                                    HashMap ht, int hv, String charset) {
        int len = h.size();
        byte[] hlen = getBytes(new Integer(len));

        stream.write(__a);
        stream.write(__Colon);
        stream.write(hlen, 0, hlen.length);
        stream.write(__Colon);
        stream.write(__LeftB);
        for (Iterator keys = h.keySet().iterator(); keys.hasNext(); ) {
            Object key = keys.next();

            if ((key instanceof Byte) || (key instanceof Short)
                    || (key instanceof Integer)) {
                writeInteger(stream, getBytes(key));
            } else if (key instanceof Boolean) {
                writeInteger(
                        stream,
                        new byte[]{((Boolean) key).booleanValue() ? __1 : __0});
            } else {
                writeString(stream, getBytes(key, charset));
            }
            hv = serialize(stream, h.get(key), ht, hv, charset);
        }
        stream.write(__RightB);
        return hv;
    }

    private static int writeObject(ByteArrayOutputStream stream, Object obj,
                                   HashMap ht, int hv, String charset) {
        Class cls = obj.getClass();

        if (obj instanceof java.io.Serializable) {
            byte[] className = getBytes(getClassName(cls), charset);
            byte[] classNameLen = getBytes(new Integer(className.length));

            if (obj instanceof org.phprpc.util.Serializable) {
                byte[] cs = ((org.phprpc.util.Serializable) obj).serialize();
                byte[] cslen = getBytes(new Integer(cs.length));

                stream.write(__C);
                stream.write(__Colon);
                stream.write(classNameLen, 0, classNameLen.length);
                stream.write(__Colon);
                stream.write(__Quote);
                stream.write(className, 0, className.length);
                stream.write(__Quote);
                stream.write(__Colon);
                stream.write(cslen, 0, cslen.length);
                stream.write(__Colon);
                stream.write(__LeftB);
                stream.write(cs, 0, cs.length);
                stream.write(__RightB);
            } else {
                Method __sleep;

                try {
                    __sleep = cls.getMethod("__sleep", new Class[0]);
                } catch (Exception e) {
                    __sleep = null;
                }
                int fl = 0;
                Field[] f;

                if (__sleep != null) {
                    String[] fieldNames;

                    try {
                        __sleep.setAccessible(true);
                        fieldNames = (String[]) __sleep.invoke(obj,
                                new Object[0]);
                    } catch (Exception e) {
                        fieldNames = null;
                    }
                    f = getFields(obj, fieldNames);
                } else {
                    f = getFields(obj);
                }
                AccessibleObject.setAccessible(f, true);
                byte[] flen = getBytes(new Integer(f.length));

                stream.write(__O);
                stream.write(__Colon);
                stream.write(classNameLen, 0, classNameLen.length);
                stream.write(__Colon);
                stream.write(__Quote);
                stream.write(className, 0, className.length);
                stream.write(__Quote);
                stream.write(__Colon);
                stream.write(flen, 0, flen.length);
                stream.write(__Colon);
                stream.write(__LeftB);
                for (int i = 0, len = f.length; i < len; i++) {
                    int mod = f[i].getModifiers();

                    if (Modifier.isPublic(mod)) {
                        writeString(stream, getBytes(f[i].getName(), charset));
                    } else if (Modifier.isProtected(mod)) {
                        writeString(stream,
                                getBytes("*" + f[i].getName(), charset));
                    } else {
                        writeString(
                                stream,
                                getBytes(
                                        ""
                                                + getClassName(f[i]
                                                .getDeclaringClass())
                                                + "" + f[i].getName(), charset));
                    }
                    Object o;

                    try {
                        o = f[i].get(obj);
                    } catch (Exception e) {
                        o = null;
                    }
                    hv = serialize(stream, o, ht, hv, charset);
                }
                stream.write(__RightB);
            }
        } else {
            writeNull(stream);
        }
        return hv;
    }

    private static byte[] getBytes(Object obj) {
        try {
            return obj.toString().getBytes("US-ASCII");
        } catch (Exception e) {
            return obj.toString().getBytes();
        }
    }

    private static byte[] getBytes(Object obj, String charset) {
        try {
            return obj.toString().getBytes(charset);
        } catch (Exception e) {
            return obj.toString().getBytes();
        }
    }

    private static String getString(byte[] data, String charset) {
        try {
            return new String(data, charset);
        } catch (Exception e) {
            return new String(data);
        }
    }

    private static Class getClass(String className) {
        try {
            Class cls = Class.forName(className);

            return cls;
        } catch (Exception e) {
        }
        for (int i = 0; i < __packages.length; i++) {
            try {
                Class cls = Class.forName(__packages[i].getName() + "."
                        + className);

                return cls;
            } catch (Exception e) {
            }
        }
        return null;
    }

    private static String getClassName(Class cls) {
        return cls.getName().substring(cls.getPackage().getName().length() + 1);
    }

    private static Field getField(Object obj, String fieldName) {
        Class cls = obj.getClass();

        while (cls != null) {
            try {
                Field result = cls.getDeclaredField(fieldName);
                int mod = result.getModifiers();

                if (Modifier.isFinal(mod) || Modifier.isStatic(mod)) {
                    return null;
                }
                return result;
            } catch (Exception e) {
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    private static Field[] getFields(Object obj, String[] fieldNames) {
        if (fieldNames == null) {
            return getFields(obj);
        }
        int n = fieldNames.length;
        ArrayList fields = new ArrayList(n);

        for (int i = 0; i < n; i++) {
            Field f = getField(obj, fieldNames[i]);

            if (f != null) {
                fields.add(f);
            }
        }
        return (Field[]) fields.toArray(new Field[0]);
    }

    private static Field[] getFields(Object obj) {
        ArrayList fields = new ArrayList();
        Class cls = obj.getClass();

        while (cls != null) {
            Field[] fs = cls.getDeclaredFields();

            for (int i = 0; i < fs.length; i++) {
                int mod = fs[i].getModifiers();

                if (!Modifier.isFinal(mod) && !Modifier.isStatic(mod)) {
                    fields.add(fs[i]);
                }
            }
            cls = cls.getSuperclass();
        }
        return (Field[]) fields.toArray(new Field[0]);
    }

    public static Object newInstance(Class cls) {
        try {
            Constructor ctor = cls.getConstructor(new Class[0]);
            int mod = ctor.getModifiers();

            if (Modifier.isPublic(mod)) {
                return ctor.newInstance(new Object[0]);
            }
        } catch (Exception e) {
        }
        try {
            Constructor ctor = cls.getConstructor(new Class[]{Integer.TYPE});
            int mod = ctor.getModifiers();

            if (Modifier.isPublic(mod)) {
                return ctor.newInstance(new Object[]{new Integer(0)});
            }
        } catch (Exception e) {
        }
        try {
            Constructor ctor = cls.getConstructor(new Class[]{Boolean.TYPE});
            int mod = ctor.getModifiers();

            if (Modifier.isPublic(mod)) {
                return ctor.newInstance(new Object[]{new Boolean(false)});
            }
        } catch (Exception e) {
        }
        try {
            Constructor ctor = cls.getConstructor(new Class[]{String.class});
            int mod = ctor.getModifiers();

            if (Modifier.isPublic(mod)) {
                return ctor.newInstance(new Object[]{""});
            }
        } catch (Exception e) {
        }
        Field[] f = cls.getFields();

        for (int i = 0; i < f.length; i++) {
            if (f[i].getType() == cls && Modifier.isStatic(f[i].getModifiers())) {
                try {
                    return f[i].get(null);
                } catch (Exception e) {
                }
            }
        }
        Method[] m = cls.getMethods();

        for (int i = 0; i < m.length; i++) {
            if (m[i].getReturnType() == cls
                    && Modifier.isStatic(m[i].getModifiers())) {
                try {
                    return m[i].invoke(null, new Object[0]);
                } catch (Exception e) {
                }
                try {
                    return m[i].invoke(null, new Object[]{new Integer(0)});
                } catch (Exception e) {
                }
                try {
                    return m[i].invoke(null,
                            new Object[]{new Boolean(false)});
                } catch (Exception e) {
                }
                try {
                    return m[i].invoke(null, new Object[]{""});
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    public static Number cast(Number n, Class destClass) {
        if (destClass == Byte.class) {
            return new Byte(n.byteValue());
        }
        if (destClass == Short.class) {
            return new Short(n.shortValue());
        }
        if (destClass == Integer.class) {
            return new Integer(n.intValue());
        }
        if (destClass == Long.class) {
            return new Long(n.longValue());
        }
        if (destClass == Float.class) {
            return new Float(n.floatValue());
        }
        if (destClass == Double.class) {
            return new Double(n.doubleValue());
        }
        return n;
    }

    public static Object cast(Object obj, Class destClass) {
        if (obj == null || destClass == null) {
            return obj;
        } else if (obj.getClass() == destClass) {
            return obj;
        } else if (obj instanceof Number) {
            return cast((Number) obj, destClass);
        } else if ((obj instanceof String) && destClass == Character.class) {
            return new Character(((String) obj).charAt(0));
        } else if ((obj instanceof ArrayList) && destClass.isArray()) {
            return toArray((ArrayList) obj, destClass.getComponentType());
        } else if ((obj instanceof ArrayList) && destClass == HashMap.class) {
            return toHashMap((ArrayList) obj);
        } else {
            return obj;
        }
    }

    private static HashMap toHashMap(ArrayList a) {
        int n = a.size();
        HashMap h = new HashMap(n);

        for (int i = 0; i < n; i++) {
            h.put(new Integer(i), a.get(i));
        }
        return h;
    }

    private static Object toArray(ArrayList obj, Class componentType) {
        int n = obj.size();
        Object a = Array.newInstance(componentType, n);

        for (int i = 0; i < n; i++) {
            Array.set(a, i, cast(obj.get(i), componentType));
        }
        return a;
    }

    private static int getPos(ByteArrayInputStream stream) {
        try {
            Field pos = stream.getClass().getDeclaredField("pos");

            pos.setAccessible(true);
            return pos.getInt(stream);
        } catch (Exception e) {
            return 0;
        }
    }

    private static void setPos(ByteArrayInputStream stream, int p) {
        try {
            Field pos = stream.getClass().getDeclaredField("pos");

            pos.setAccessible(true);
            pos.setInt(stream, p);
        } catch (Exception e) {
        }
    }

    public static Object unserialize(byte[] ss) throws IllegalAccessException {
        return unserialize(ss, null, "UTF-8");
    }

    public static Object unserialize(byte[] ss, String charset)
            throws IllegalAccessException {
        return unserialize(ss, null, charset);
    }

    public static Object unserialize(byte[] ss, Class cls)
            throws IllegalAccessException {
        return unserialize(ss, cls, "UTF-8");
    }

    public static Object unserialize(byte[] ss, Class cls, String charset)
            throws IllegalAccessException {
        int hv = 1;
        ByteArrayInputStream stream = new ByteArrayInputStream(ss);
        Object result = unserialize(stream, new HashMap(), hv, new HashMap(), charset).value;

        try {
            stream.close();
        } catch (Exception e) {
        }
        return cast(result, cls);
    }

    private static UnSerializeResult unserialize(ByteArrayInputStream stream,
                                                 HashMap ht, int hv, HashMap rt, String charset)
            throws IllegalAccessException {
        Object obj;

        switch (stream.read()) {
            case __N:
                obj = readNull(stream);
                ht.put(new Integer(hv++), obj);
                return new UnSerializeResult(obj, hv);

            case __b:
                obj = readBoolean(stream);
                ht.put(new Integer(hv++), obj);
                return new UnSerializeResult(obj, hv);

            case __i:
                obj = readInteger(stream);
                ht.put(new Integer(hv++), obj);
                return new UnSerializeResult(obj, hv);

            case __d:
                obj = readDouble(stream);
                ht.put(new Integer(hv++), obj);
                return new UnSerializeResult(obj, hv);

            case __s:
                obj = readString(stream, charset);
                ht.put(new Integer(hv++), obj);
                return new UnSerializeResult(obj, hv);

            case __U:
                obj = readUnicodeString(stream);
                ht.put(new Integer(hv++), obj);
                return new UnSerializeResult(obj, hv);

            case __r:
                return readRef(stream, ht, hv, rt);

            case __a:
                return readArray(stream, ht, hv, rt, charset);

            case __O:
                return readObject(stream, ht, hv, rt, charset);

            case __C:
                return readCustomObject(stream, ht, hv, charset);

            case __R:
                return readPointRef(stream, ht, hv, rt);

            default:
                return null;
        }
    }

    private static String readNumber(ByteArrayInputStream stream) {
        StringBuffer sb = new StringBuffer();
        int i = stream.read();

        while ((i != __Semicolon) && (i != __Colon)) {
            sb.append((char) i);
            i = stream.read();
        }
        return sb.toString();
    }

    private static Object readNull(ByteArrayInputStream stream) {
        stream.skip(1);
        return null;
    }

    private static Boolean readBoolean(ByteArrayInputStream stream) {
        stream.skip(1);
        Boolean b = new Boolean(stream.read() == __1);

        stream.skip(1);
        return b;
    }

    private static Number readInteger(ByteArrayInputStream stream) {
        stream.skip(1);
        String i = readNumber(stream);

        try {
            return new Byte(i);
        } catch (Exception e1) {
            try {
                return new Short(i);
            } catch (Exception e2) {
                return new Integer(i);
            }
        }
    }

    private static Number readDouble(ByteArrayInputStream stream) {
        stream.skip(1);
        String d = readNumber(stream);

        if (d.equals(__NAN)) {
            return new Double(Double.NaN);
        }
        if (d.equals(__INF)) {
            return new Double(Double.POSITIVE_INFINITY);
        }
        if (d.equals(__NINF)) {
            return new Double(Double.NEGATIVE_INFINITY);
        }
        try {
            return new Long(d);
        } catch (Exception e1) {
            try {
                Float f = new Float(d);

                if (f.isInfinite()) {
                    return new Double(d);
                } else {
                    return f;
                }
            } catch (Exception e2) {
                return new Float(0);
            }
        }
    }

    private static String readString(ByteArrayInputStream stream, String charset) {
        stream.skip(1);
        int len = Integer.parseInt(readNumber(stream));

        stream.skip(1);
        byte[] buf = new byte[len];

        stream.read(buf, 0, len);
        String s = getString(buf, charset);

        stream.skip(2);
        return s;
    }

    private static String readUnicodeString(ByteArrayInputStream stream) {
        stream.skip(1);
        int l = Integer.parseInt(readNumber(stream));

        stream.skip(1);
        StringBuffer sb = new StringBuffer(l);
        int c;

        for (int i = 0; i < l; i++) {
            if ((c = stream.read()) == __Slash) {
                char c1 = (char) stream.read();
                char c2 = (char) stream.read();
                char c3 = (char) stream.read();
                char c4 = (char) stream.read();

                sb.append((char) (Integer.parseInt(new String(new char[]{c1,
                        c2, c3, c4}), 16)));
            } else {
                sb.append((char) c);
            }
        }
        stream.skip(2);
        return sb.toString();
    }

    private static UnSerializeResult readRef(ByteArrayInputStream stream,
                                             HashMap ht, int hv, HashMap rt) {
        stream.skip(1);
        Integer r = new Integer(readNumber(stream));

        if (rt.containsKey(r)) {
            rt.put(r, new Boolean(true));
        }
        Object obj = ht.get(r);

        ht.put(new Integer(hv++), obj);
        return new UnSerializeResult(obj, hv);
    }

    private static UnSerializeResult readPointRef(ByteArrayInputStream stream,
                                                  HashMap ht, int hv, HashMap rt) {
        stream.skip(1);
        Integer r = new Integer(readNumber(stream));

        if (rt.containsKey(r)) {
            rt.put(r, new Boolean(true));
        }
        Object obj = ht.get(r);

        return new UnSerializeResult(obj, hv);
    }

    private static UnSerializeResult readArray(ByteArrayInputStream stream,
                                               HashMap ht, int hv, HashMap rt, String charset)
            throws IllegalAccessException {
        stream.skip(1);
        int n = Integer.parseInt(readNumber(stream));

        stream.skip(1);
        HashMap h = new HashMap(n);
        ArrayList al = new ArrayList(n);
        Integer r = new Integer(hv);

        rt.put(r, new Boolean(false));
        int p = getPos(stream);

        ht.put(new Integer(hv++), h);
        for (int i = 0; i < n; i++) {
            Object key;

            switch (stream.read()) {
                case __i:
                    key = cast(readInteger(stream), Integer.class);
                    break;

                case __s:
                    key = readString(stream, charset);
                    break;

                case __U:
                    key = readUnicodeString(stream);
                    break;

                default:
                    return null;
            }
            UnSerializeResult result = unserialize(stream, ht, hv, rt, charset);

            hv = result.hv;
            if (al != null) {
                if ((key instanceof Integer)
                        && (((Integer) key).intValue() == i)) {
                    al.add(result.value);
                } else {
                    al = null;
                }
            }
            h.put(key, result.value);
        }
        if (al != null) {
            ht.put(r, al);
            if (((Boolean) (rt.get(r))).booleanValue()) {
                hv = r.intValue() + 1;
                setPos(stream, p);
                for (int i = 0; i < n; i++) {
                    int key;

                    switch (stream.read()) {
                        case __i:
                            key = ((Integer) cast(readInteger(stream),
                                    Integer.class)).intValue();
                            break;

                        default:
                            return null;
                    }
                    UnSerializeResult result = unserialize(stream, ht, hv, rt,
                            charset);

                    hv = result.hv;
                    al.set(key, result.value);
                }
            }
        }
        rt.remove(r);
        stream.skip(1);
        return new UnSerializeResult(ht.get(r), hv);
    }

    private static UnSerializeResult readObject(ByteArrayInputStream stream,
                                                HashMap ht, int hv, HashMap rt, String charset)
            throws IllegalAccessException {
        stream.skip(1);
        int len = Integer.parseInt(readNumber(stream));

        stream.skip(1);
        byte[] buf = new byte[len];

        stream.read(buf, 0, len);
        String cn = getString(buf, charset);

        stream.skip(2);
        int n = Integer.parseInt(readNumber(stream));

        stream.skip(1);
        Class cls = getClass(cn);
        Object o;

        if (cls != null) {
            if ((o = newInstance(cls)) == null) {
                o = new HashMap(n);
            }
        } else {
            o = new HashMap(n);
        }
        ht.put(new Integer(hv++), o);
        for (int i = 0; i < n; i++) {
            String key;

            switch (stream.read()) {
                case __s:
                    key = readString(stream, charset);
                    break;

                case __U:
                    key = readUnicodeString(stream);
                    break;

                default:
                    return null;
            }
            if (key.charAt(0) == (char) 0) {
                key = key.substring(key.indexOf("", 1) + 1);
            }
            UnSerializeResult result = unserialize(stream, ht, hv, rt, charset);

            hv = result.hv;
            if (o instanceof HashMap) {
                ((HashMap) o).put(key, result.value);
            } else {
                Field f = getField(o, key);

                f.setAccessible(true);
                f.set(o, result.value);
            }
        }
        stream.skip(1);
        Method __wakeup = null;

        try {
            __wakeup = o.getClass().getMethod("__wakeup", new Class[0]);
            __wakeup.invoke(o, new Object[0]);
        } catch (Exception e) {
        }
        return new UnSerializeResult(o, hv);
    }

    private static UnSerializeResult readCustomObject(
            ByteArrayInputStream stream, HashMap ht, int hv, String charset) {
        stream.skip(1);
        int len = Integer.parseInt(readNumber(stream));

        stream.skip(1);
        byte[] buf = new byte[len];

        stream.read(buf, 0, len);
        String cn = getString(buf, charset);

        stream.skip(2);
        int n = Integer.parseInt(readNumber(stream));

        stream.skip(1);
        Class cls = getClass(cn);
        Object o;

        if (cls != null) {
            o = newInstance(cls);
        } else {
            o = null;
        }
        ht.put(new Integer(hv++), o);
        if (o == null) {
            stream.skip(n);
        } else if (o instanceof org.phprpc.util.Serializable) {
            byte[] b = new byte[n];

            stream.read(b, 0, n);
            ((org.phprpc.util.Serializable) o).unserialize(b);
        } else {
            stream.skip(n);
        }
        stream.skip(1);
        return new UnSerializeResult(o, hv);
    }

    @Test
    public void php() throws UnsupportedEncodingException, IllegalAccessException {
        String content = "a:858:{s:19:\"menu_mall_manage_16\";i:0;s:19:\"menu_mall_manage_17\";i:0;s:19:\"menu_mall_manage_18\";i:0;s:19:\"menu_mall_manage_19\";i:0;s:19:\"menu_mall_manage_12\";i:0;s:19:\"menu_mall_manage_13\";i:0;s:19:\"menu_mall_manage_14\";i:0;s:19:\"menu_mall_manage_15\";i:0;s:26:\"menu_receipt_management_53\";i:1;s:26:\"menu_receipt_management_52\";i:1;s:26:\"menu_finance_management2_9\";i:0;s:26:\"menu_finance_management2_8\";i:0;s:26:\"menu_receipt_management_54\";i:1;s:30:\"menu_new_finance_management_38\";i:1;s:30:\"menu_new_finance_management_37\";i:1;s:19:\"menu_mall_manage_10\";i:0;s:30:\"menu_new_finance_management_39\";i:0;s:19:\"menu_mall_manage_11\";i:0;s:30:\"menu_new_finance_management_36\";i:1;s:30:\"menu_new_finance_management_35\";i:1;s:11:\"menu_sms_14\";i:0;s:11:\"menu_sms_13\";i:0;s:11:\"menu_sms_16\";i:0;s:11:\"menu_sms_15\";i:0;s:11:\"menu_sms_10\";i:0;s:11:\"menu_mail_3\";i:0;s:11:\"menu_mail_2\";i:0;s:11:\"menu_sms_12\";i:0;s:11:\"menu_mail_1\";i:0;s:11:\"menu_sms_11\";i:0;s:11:\"menu_mail_0\";i:0;s:11:\"menu_mail_7\";i:0;s:17:\"menu_black_list_0\";i:0;s:11:\"menu_mail_6\";i:0;s:11:\"menu_mail_5\";i:0;s:11:\"menu_mail_4\";i:0;s:17:\"menu_black_list_3\";i:0;s:17:\"menu_black_list_1\";i:0;s:17:\"menu_black_list_2\";i:0;s:25:\"menu_credit_management_11\";i:0;s:25:\"menu_credit_management_12\";i:0;s:25:\"menu_credit_management_10\";i:0;s:25:\"menu_credit_management_15\";i:0;s:25:\"menu_credit_management_16\";i:0;s:25:\"menu_credit_management_13\";i:0;s:30:\"menu_new_finance_management_28\";i:1;s:25:\"menu_credit_management_14\";i:0;s:30:\"menu_new_finance_management_23\";i:1;s:30:\"menu_new_finance_management_22\";i:1;s:30:\"menu_new_finance_management_24\";i:1;s:24:\"menu_credit_management_0\";i:0;s:20:\"menu_activity_logs_8\";i:0;s:24:\"menu_credit_management_1\";i:0;s:24:\"menu_credit_management_2\";i:0;s:24:\"menu_credit_management_3\";i:0;s:24:\"menu_credit_management_4\";i:0;s:24:\"menu_credit_management_5\";i:0;s:20:\"menu_activity_logs_2\";i:0;s:20:\"menu_activity_logs_3\";i:0;s:20:\"menu_activity_logs_0\";i:0;s:20:\"menu_activity_logs_1\";i:0;s:20:\"menu_activity_logs_6\";i:0;s:20:\"menu_activity_logs_7\";i:0;s:20:\"menu_activity_logs_4\";i:0;s:20:\"menu_activity_logs_5\";i:0;s:21:\"menu_ad_mamagement_51\";i:0;s:21:\"menu_ad_mamagement_50\";i:0;s:20:\"menu_push_messages_4\";i:0;s:20:\"menu_push_messages_2\";i:0;s:20:\"menu_push_messages_3\";i:0;s:26:\"menu_finance_management2_0\";i:0;s:20:\"menu_push_messages_0\";i:0;s:20:\"menu_push_messages_1\";i:0;s:26:\"menu_receipt_management_20\";i:0;s:26:\"menu_receipt_management_22\";i:0;s:26:\"menu_receipt_management_21\";i:0;s:24:\"menu_credit_management_6\";i:0;s:19:\"menu_mall_manage_20\";i:0;s:24:\"menu_credit_management_7\";i:0;s:19:\"menu_mall_manage_21\";i:0;s:24:\"menu_credit_management_8\";i:0;s:21:\"menu_ad_mamagement_49\";i:0;s:19:\"menu_mall_manage_22\";i:0;s:24:\"menu_credit_management_9\";i:0;s:21:\"menu_ad_mamagement_48\";i:0;s:21:\"menu_ad_mamagement_47\";i:0;s:21:\"menu_ad_mamagement_46\";i:0;s:21:\"menu_ad_mamagement_45\";i:0;s:28:\"menu_admin_user_mamagement_3\";i:0;s:28:\"menu_admin_user_mamagement_4\";i:0;s:28:\"menu_admin_user_mamagement_5\";i:0;s:28:\"menu_admin_user_mamagement_6\";i:0;s:28:\"menu_admin_user_mamagement_0\";i:0;s:28:\"menu_admin_user_mamagement_1\";i:0;s:28:\"menu_admin_user_mamagement_2\";i:0;s:19:\"menu_call_center_10\";i:0;s:19:\"menu_call_center_11\";i:0;s:19:\"menu_call_center_12\";i:0;s:19:\"menu_call_center_13\";i:0;s:19:\"menu_call_center_14\";i:0;s:19:\"menu_call_center_15\";i:0;s:19:\"menu_call_center_16\";i:0;s:24:\"menu_guwen_management_12\";i:0;s:24:\"menu_guwen_management_13\";i:0;s:24:\"menu_guwen_management_14\";i:0;s:24:\"menu_guwen_management_15\";i:0;s:24:\"menu_guwen_management_16\";i:0;s:24:\"menu_guwen_management_10\";i:0;s:24:\"menu_guwen_management_11\";i:0;s:15:\"menu_reg_data_1\";i:0;s:15:\"menu_reg_data_0\";i:0;s:28:\"menu_admin_user_mamagement_7\";i:0;s:21:\"menu_cus_statistics_2\";i:0;s:21:\"menu_cus_statistics_3\";i:0;s:21:\"menu_cus_statistics_0\";i:0;s:15:\"menu_reg_data_2\";i:0;s:21:\"menu_cus_statistics_1\";i:0;s:18:\"menu_reg_member_98\";i:1;s:18:\"menu_reg_member_97\";i:1;s:23:\"menu_inner_user_vote_10\";i:0;s:18:\"menu_reg_member_99\";i:1;s:18:\"menu_reg_member_90\";i:1;s:10:\"menu_sms_1\";i:0;s:26:\"menu_product_management_10\";i:0;s:10:\"menu_sms_0\";i:0;s:18:\"menu_reg_member_92\";i:1;s:18:\"menu_reg_member_91\";i:1;s:18:\"menu_reg_member_94\";i:1;s:26:\"menu_product_management_13\";i:0;s:10:\"menu_sms_5\";i:0;s:18:\"menu_reg_member_93\";i:1;s:26:\"menu_product_management_14\";i:0;s:10:\"menu_sms_4\";i:0;s:18:\"menu_reg_member_96\";i:1;s:26:\"menu_product_management_11\";i:0;s:10:\"menu_sms_3\";i:0;s:18:\"menu_reg_member_95\";i:1;s:26:\"menu_product_management_12\";i:0;s:10:\"menu_sms_2\";i:0;s:26:\"menu_product_management_17\";i:0;s:10:\"menu_sms_9\";i:0;s:26:\"menu_product_management_18\";i:0;s:10:\"menu_sms_8\";i:0;s:26:\"menu_product_management_15\";i:0;s:10:\"menu_sms_7\";i:0;s:26:\"menu_product_management_16\";i:0;s:10:\"menu_sms_6\";i:0;s:26:\"menu_product_management_19\";i:0;s:26:\"menu_gvoucher_management_8\";i:0;s:26:\"menu_gvoucher_management_9\";i:0;s:26:\"menu_gvoucher_management_6\";i:0;s:26:\"menu_gvoucher_management_7\";i:0;s:26:\"menu_gvoucher_management_4\";i:0;s:26:\"menu_gvoucher_management_5\";i:0;s:26:\"menu_gvoucher_management_2\";i:0;s:26:\"menu_gvoucher_management_3\";i:0;s:26:\"menu_gvoucher_management_0\";i:0;s:26:\"menu_gvoucher_management_1\";i:0;s:21:\"menu_ad_mamagement_44\";i:0;s:21:\"menu_ad_mamagement_43\";i:0;s:21:\"menu_ad_mamagement_42\";i:0;s:21:\"menu_ad_mamagement_41\";i:0;s:21:\"menu_ad_mamagement_40\";i:0;s:26:\"menu_receipt_management_13\";i:0;s:26:\"menu_receipt_management_12\";i:0;s:26:\"menu_receipt_management_11\";i:0;s:16:\"menu_click_num_0\";i:1;s:26:\"menu_receipt_management_10\";i:0;s:16:\"menu_click_num_1\";i:1;s:26:\"menu_receipt_management_17\";i:0;s:26:\"menu_receipt_management_16\";i:0;s:26:\"menu_receipt_management_15\";i:0;s:21:\"menu_ad_mamagement_39\";i:0;s:26:\"menu_receipt_management_14\";i:0;s:21:\"menu_ad_mamagement_38\";i:0;s:21:\"menu_ad_mamagement_37\";i:0;s:21:\"menu_ad_mamagement_36\";i:0;s:26:\"menu_receipt_management_19\";i:0;s:21:\"menu_ad_mamagement_35\";i:0;s:26:\"menu_receipt_management_18\";i:0;s:21:\"menu_ad_mamagement_34\";i:0;s:21:\"menu_ad_mamagement_33\";i:0;s:21:\"menu_ad_mamagement_32\";i:0;s:17:\"menu_user_trans_4\";i:0;s:17:\"menu_user_trans_3\";i:0;s:17:\"menu_user_trans_6\";i:0;s:17:\"menu_user_trans_5\";i:0;s:17:\"menu_user_trans_8\";i:0;s:17:\"menu_user_trans_7\";i:0;s:17:\"menu_user_trans_9\";i:0;s:25:\"menu_receipt_management_0\";i:0;s:25:\"menu_receipt_management_4\";i:0;s:17:\"menu_user_trans_0\";i:0;s:25:\"menu_receipt_management_3\";i:0;s:25:\"menu_receipt_management_2\";i:0;s:17:\"menu_user_trans_2\";i:0;s:25:\"menu_receipt_management_1\";i:0;s:17:\"menu_user_trans_1\";i:0;s:25:\"menu_receipt_management_8\";i:0;s:18:\"menu_call_center_6\";i:0;s:25:\"menu_receipt_management_7\";i:0;s:21:\"menu_ad_mamagement_21\";i:0;s:18:\"menu_call_center_5\";i:0;s:25:\"menu_receipt_management_6\";i:0;s:21:\"menu_ad_mamagement_20\";i:0;s:18:\"menu_call_center_8\";i:0;s:25:\"menu_receipt_management_5\";i:0;s:18:\"menu_call_center_7\";i:0;s:18:\"menu_call_center_2\";i:0;s:18:\"menu_call_center_1\";i:0;s:18:\"menu_call_center_4\";i:0;s:25:\"menu_receipt_management_9\";i:0;s:18:\"menu_call_center_3\";i:0;s:26:\"menu_product_management_31\";i:1;s:26:\"menu_product_management_32\";i:0;s:18:\"menu_call_center_0\";i:0;s:26:\"menu_product_management_30\";i:1;s:26:\"menu_product_management_35\";i:0;s:26:\"menu_product_management_36\";i:0;s:26:\"menu_product_management_33\";i:0;s:26:\"menu_product_management_34\";i:0;s:26:\"menu_product_management_37\";i:0;s:21:\"menu_ad_mamagement_19\";i:0;s:21:\"menu_ad_mamagement_18\";i:0;s:21:\"menu_ad_mamagement_17\";i:0;s:21:\"menu_ad_mamagement_16\";i:0;s:21:\"menu_ad_mamagement_15\";i:0;s:21:\"menu_ad_mamagement_14\";i:0;s:18:\"menu_call_center_9\";i:0;s:21:\"menu_ad_mamagement_13\";i:0;s:21:\"menu_ad_mamagement_12\";i:0;s:21:\"menu_ad_mamagement_11\";i:0;s:26:\"menu_product_management_20\";i:0;s:26:\"menu_close_dynamicToken_11\";i:0;s:26:\"menu_product_management_21\";i:1;s:26:\"menu_close_dynamicToken_12\";i:0;s:26:\"menu_close_dynamicToken_10\";i:0;s:26:\"menu_product_management_25\";i:0;s:26:\"menu_product_management_28\";i:0;s:26:\"menu_product_management_29\";i:1;s:26:\"menu_product_management_26\";i:0;s:26:\"menu_product_management_27\";i:0;s:18:\"menu_reg_member_10\";i:1;s:18:\"menu_reg_member_12\";i:1;s:18:\"menu_reg_member_11\";i:1;s:18:\"menu_reg_member_14\";i:1;s:18:\"menu_reg_member_13\";i:0;s:18:\"menu_reg_member_16\";i:0;s:18:\"menu_reg_member_15\";i:0;s:27:\"menu_fuwushang_management_4\";i:0;s:27:\"menu_fuwushang_management_3\";i:0;s:27:\"menu_fuwushang_management_2\";i:0;s:27:\"menu_fuwushang_management_1\";i:0;s:27:\"menu_fuwushang_management_0\";i:0;s:18:\"menu_reg_member_21\";i:1;s:18:\"menu_reg_member_20\";i:1;s:18:\"menu_reg_member_23\";i:1;s:18:\"menu_reg_member_22\";i:1;s:18:\"menu_reg_member_25\";i:1;s:23:\"menu_user_distribute_18\";i:0;s:18:\"menu_reg_member_24\";i:1;s:23:\"menu_user_distribute_17\";i:0;s:18:\"menu_reg_member_27\";i:0;s:18:\"menu_reg_member_26\";i:1;s:23:\"menu_user_distribute_19\";i:0;s:19:\"menu_reg_member_107\";i:1;s:19:\"menu_reg_member_108\";i:1;s:19:\"menu_reg_member_105\";i:1;s:19:\"menu_reg_member_106\";i:1;s:19:\"menu_reg_member_103\";i:1;s:19:\"menu_reg_member_104\";i:1;s:19:\"menu_reg_member_101\";i:1;s:27:\"menu_fuwushang_management_6\";i:0;s:19:\"menu_reg_member_102\";i:1;s:27:\"menu_fuwushang_management_5\";i:0;s:18:\"menu_reg_member_18\";i:1;s:23:\"menu_user_distribute_14\";i:0;s:18:\"menu_reg_member_17\";i:1;s:19:\"menu_reg_member_100\";i:1;s:23:\"menu_user_distribute_13\";i:0;s:23:\"menu_user_distribute_16\";i:0;s:18:\"menu_reg_member_19\";i:1;s:23:\"menu_user_distribute_15\";i:0;s:23:\"menu_user_distribute_10\";i:0;s:23:\"menu_user_distribute_12\";i:0;s:23:\"menu_user_distribute_11\";i:0;s:18:\"menu_reg_member_32\";i:1;s:18:\"menu_reg_member_31\";i:0;s:18:\"menu_reg_member_34\";i:1;s:18:\"menu_reg_member_33\";i:1;s:18:\"menu_reg_member_36\";i:1;s:23:\"menu_user_distribute_29\";i:0;s:18:\"menu_reg_member_35\";i:1;s:23:\"menu_user_distribute_28\";i:0;s:18:\"menu_reg_member_38\";i:1;s:18:\"menu_reg_member_37\";i:1;s:18:\"menu_reg_member_30\";i:1;s:19:\"menu_reg_member_109\";i:1;s:19:\"menu_reg_member_118\";i:1;s:19:\"menu_reg_member_119\";i:1;s:19:\"menu_reg_member_116\";i:1;s:19:\"menu_reg_member_117\";i:1;s:19:\"menu_reg_member_114\";i:1;s:19:\"menu_reg_member_115\";i:1;s:19:\"menu_reg_member_112\";i:1;s:19:\"menu_reg_member_113\";i:1;s:18:\"menu_reg_member_29\";i:1;s:19:\"menu_reg_member_110\";i:1;s:23:\"menu_user_distribute_25\";i:0;s:18:\"menu_reg_member_28\";i:1;s:19:\"menu_reg_member_111\";i:1;s:23:\"menu_user_distribute_24\";i:0;s:23:\"menu_user_distribute_27\";i:0;s:23:\"menu_user_distribute_26\";i:0;s:23:\"menu_user_distribute_21\";i:0;s:23:\"menu_user_distribute_20\";i:0;s:23:\"menu_user_distribute_23\";i:0;s:23:\"menu_user_distribute_22\";i:0;s:18:\"menu_reg_member_43\";i:1;s:18:\"menu_reg_member_42\";i:1;s:18:\"menu_reg_member_47\";i:1;s:18:\"menu_reg_member_46\";i:1;s:23:\"menu_user_distribute_39\";i:0;s:18:\"menu_reg_member_48\";i:1;s:18:\"menu_reg_member_41\";i:1;s:18:\"menu_reg_member_40\";i:1;s:19:\"menu_reg_member_129\";i:0;s:20:\"menu_ad_mamagement_8\";i:0;s:18:\"menu_mall_manage_2\";i:0;s:20:\"menu_ad_mamagement_7\";i:0;s:18:\"menu_mall_manage_1\";i:0;s:19:\"menu_reg_member_127\";i:1;s:23:\"menu_user_distribute_30\";i:0;s:18:\"menu_mall_manage_0\";i:0;s:19:\"menu_reg_member_128\";i:0;s:20:\"menu_ad_mamagement_9\";i:0;s:19:\"menu_reg_member_125\";i:1;s:18:\"menu_mall_manage_6\";i:0;s:19:\"menu_reg_member_126\";i:1;s:18:\"menu_mall_manage_5\";i:0;s:19:\"menu_reg_member_123\";i:1;s:18:\"menu_mall_manage_4\";i:0;s:19:\"menu_reg_member_124\";i:1;s:18:\"menu_mall_manage_3\";i:0;s:19:\"menu_reg_member_121\";i:1;s:23:\"menu_user_distribute_36\";i:0;s:20:\"menu_ad_mamagement_0\";i:0;s:18:\"menu_reg_member_39\";i:1;s:19:\"menu_reg_member_122\";i:1;s:18:\"menu_mall_manage_9\";i:0;s:23:\"menu_user_distribute_38\";i:0;s:20:\"menu_ad_mamagement_2\";i:0;s:18:\"menu_mall_manage_8\";i:0;s:19:\"menu_reg_member_120\";i:1;s:23:\"menu_user_distribute_37\";i:0;s:20:\"menu_ad_mamagement_1\";i:0;s:18:\"menu_mall_manage_7\";i:0;s:23:\"menu_user_distribute_32\";i:0;s:20:\"menu_ad_mamagement_4\";i:0;s:23:\"menu_user_distribute_31\";i:0;s:20:\"menu_ad_mamagement_3\";i:0;s:20:\"menu_ad_mamagement_6\";i:0;s:20:\"menu_ad_mamagement_5\";i:0;s:18:\"menu_reg_member_54\";i:1;s:19:\"menu_company_auth_0\";i:1;s:18:\"menu_reg_member_53\";i:1;s:18:\"menu_reg_member_56\";i:1;s:18:\"menu_reg_member_55\";i:1;s:18:\"menu_reg_member_58\";i:1;s:19:\"menu_company_auth_4\";i:0;s:18:\"menu_reg_member_57\";i:1;s:19:\"menu_company_auth_3\";i:0;s:19:\"menu_company_auth_2\";i:0;s:18:\"menu_reg_member_59\";i:1;s:19:\"menu_company_auth_1\";i:1;s:19:\"menu_admin_member_1\";i:1;s:19:\"menu_admin_member_2\";i:1;s:19:\"menu_admin_member_3\";i:1;s:19:\"menu_admin_member_4\";i:1;s:18:\"menu_reg_member_52\";i:1;s:17:\"menu_cps_manage_0\";i:0;s:18:\"menu_reg_member_51\";i:1;s:19:\"menu_admin_member_0\";i:1;s:17:\"menu_cps_manage_2\";i:0;s:17:\"menu_cps_manage_1\";i:0;s:19:\"menu_reg_member_138\";i:0;s:17:\"menu_cps_manage_4\";i:0;s:19:\"menu_reg_member_139\";i:0;s:17:\"menu_cps_manage_3\";i:0;s:19:\"menu_reg_member_136\";i:0;s:17:\"menu_cps_manage_6\";i:0;s:19:\"menu_reg_member_137\";i:0;s:17:\"menu_cps_manage_5\";i:0;s:19:\"menu_reg_member_134\";i:1;s:17:\"menu_cps_manage_8\";i:0;s:19:\"menu_reg_member_135\";i:0;s:17:\"menu_cps_manage_7\";i:0;s:19:\"menu_reg_member_132\";i:0;s:19:\"menu_reg_member_133\";i:1;s:17:\"menu_cps_manage_9\";i:0;s:19:\"menu_reg_member_130\";i:0;s:19:\"menu_reg_member_131\";i:0;s:18:\"menu_reg_member_65\";i:1;s:18:\"menu_reg_member_64\";i:1;s:18:\"menu_reg_member_66\";i:1;s:27:\"menu_receipt_management_101\";i:1;s:27:\"menu_receipt_management_100\";i:1;s:18:\"menu_reg_member_60\";i:1;s:18:\"menu_reg_member_63\";i:1;s:19:\"menu_admin_member_9\";i:0;s:19:\"menu_reg_member_149\";i:0;s:19:\"menu_reg_member_147\";i:0;s:19:\"menu_admin_member_5\";i:1;s:19:\"menu_reg_member_148\";i:0;s:19:\"menu_admin_member_6\";i:1;s:19:\"menu_reg_member_145\";i:1;s:19:\"menu_admin_member_7\";i:1;s:19:\"menu_reg_member_146\";i:0;s:19:\"menu_admin_member_8\";i:0;s:19:\"menu_reg_member_143\";i:0;s:19:\"menu_reg_member_144\";i:1;s:19:\"menu_reg_member_141\";i:0;s:19:\"menu_reg_member_142\";i:0;s:19:\"menu_reg_member_140\";i:0;s:18:\"menu_reg_member_76\";i:1;s:23:\"menu_tally_management_3\";i:0;s:18:\"menu_reg_member_75\";i:1;s:23:\"menu_tally_management_4\";i:0;s:18:\"menu_reg_member_78\";i:1;s:23:\"menu_tally_management_1\";i:0;s:18:\"menu_reg_member_77\";i:1;s:23:\"menu_tally_management_2\";i:0;s:23:\"menu_tally_management_7\";i:0;s:18:\"menu_reg_member_79\";i:0;s:23:\"menu_tally_management_5\";i:0;s:23:\"menu_tally_management_6\";i:0;s:23:\"menu_tally_management_0\";i:0;s:18:\"menu_reg_member_74\";i:1;s:18:\"menu_reg_member_73\";i:1;s:19:\"menu_reg_member_158\";i:0;s:19:\"menu_reg_member_159\";i:0;s:19:\"menu_reg_member_156\";i:0;s:19:\"menu_reg_member_157\";i:0;s:19:\"menu_reg_member_154\";i:0;s:19:\"menu_reg_member_155\";i:0;s:19:\"menu_reg_member_152\";i:0;s:19:\"menu_reg_member_153\";i:0;s:19:\"menu_reg_member_150\";i:1;s:19:\"menu_reg_member_151\";i:0;s:18:\"menu_reg_member_87\";i:1;s:18:\"menu_reg_member_86\";i:1;s:18:\"menu_reg_member_89\";i:1;s:18:\"menu_reg_member_88\";i:1;s:18:\"menu_reg_member_81\";i:1;s:18:\"menu_reg_member_80\";i:0;s:18:\"menu_reg_member_83\";i:1;s:18:\"menu_reg_member_82\";i:1;s:18:\"menu_reg_member_85\";i:1;s:18:\"menu_reg_member_84\";i:1;s:19:\"menu_reg_member_169\";i:0;s:19:\"menu_reg_member_167\";i:0;s:19:\"menu_reg_member_168\";i:0;s:19:\"menu_reg_member_165\";i:0;s:19:\"menu_company_auth_8\";i:0;s:19:\"menu_reg_member_166\";i:0;s:19:\"menu_company_auth_7\";i:0;s:19:\"menu_reg_member_163\";i:0;s:19:\"menu_company_auth_6\";i:0;s:19:\"menu_reg_member_164\";i:1;s:19:\"menu_company_auth_5\";i:0;s:19:\"menu_reg_member_161\";i:1;s:19:\"menu_reg_member_162\";i:0;s:19:\"menu_reg_member_160\";i:1;s:19:\"menu_company_auth_9\";i:0;s:23:\"menu_user_distribute_81\";i:0;s:19:\"menu_reg_member_179\";i:0;s:23:\"menu_user_distribute_80\";i:0;s:19:\"menu_reg_member_177\";i:0;s:19:\"menu_reg_member_174\";i:0;s:19:\"menu_reg_member_175\";i:0;s:19:\"menu_reg_member_172\";i:1;s:19:\"menu_reg_member_173\";i:0;s:19:\"menu_reg_member_170\";i:0;s:19:\"menu_reg_member_171\";i:0;s:25:\"menu_product_management_1\";i:1;s:19:\"menu_reg_member_180\";i:1;s:25:\"menu_product_management_0\";i:1;s:25:\"menu_product_management_3\";i:0;s:25:\"menu_product_management_2\";i:0;s:25:\"menu_product_management_9\";i:0;s:25:\"menu_product_management_8\";i:0;s:25:\"menu_product_management_5\";i:0;s:25:\"menu_product_management_4\";i:0;s:25:\"menu_product_management_7\";i:0;s:25:\"menu_product_management_6\";i:0;s:19:\"menu_reg_member_187\";i:0;s:19:\"menu_reg_member_185\";i:0;s:19:\"menu_reg_member_186\";i:1;s:19:\"menu_reg_member_183\";i:0;s:19:\"menu_reg_member_184\";i:0;s:19:\"menu_reg_member_181\";i:1;s:19:\"menu_reg_member_182\";i:1;s:19:\"menu_reg_member_190\";i:0;s:19:\"menu_reg_member_191\";i:1;s:19:\"menu_reg_member_196\";i:0;s:19:\"menu_reg_member_194\";i:0;s:29:\"menu_new_finance_management_1\";i:1;s:19:\"menu_reg_member_195\";i:0;s:29:\"menu_new_finance_management_0\";i:1;s:19:\"menu_reg_member_192\";i:1;s:29:\"menu_new_finance_management_3\";i:1;s:19:\"menu_reg_member_193\";i:0;s:29:\"menu_new_finance_management_2\";i:1;s:26:\"menu_activity_management_8\";i:0;s:26:\"menu_activity_management_9\";i:0;s:23:\"menu_user_distribute_41\";i:0;s:23:\"menu_user_distribute_40\";i:0;s:26:\"menu_activity_management_4\";i:0;s:26:\"menu_activity_management_5\";i:0;s:26:\"menu_activity_management_6\";i:0;s:26:\"menu_activity_management_7\";i:0;s:23:\"menu_user_distribute_47\";i:0;s:26:\"menu_activity_management_0\";i:0;s:23:\"menu_user_distribute_46\";i:0;s:26:\"menu_activity_management_1\";i:0;s:26:\"menu_activity_management_2\";i:0;s:23:\"menu_user_distribute_48\";i:0;s:26:\"menu_activity_management_3\";i:0;s:23:\"menu_user_distribute_43\";i:0;s:23:\"menu_user_distribute_42\";i:0;s:23:\"menu_user_distribute_45\";i:0;s:23:\"menu_user_distribute_44\";i:0;s:30:\"menu_product_line_management_2\";i:0;s:30:\"menu_product_line_management_1\";i:0;s:23:\"menu_user_distribute_50\";i:0;s:23:\"menu_user_distribute_52\";i:1;s:23:\"menu_user_distribute_51\";i:0;s:23:\"menu_user_distribute_58\";i:1;s:23:\"menu_user_distribute_57\";i:1;s:23:\"menu_user_distribute_59\";i:1;s:23:\"menu_user_distribute_54\";i:1;s:30:\"menu_product_line_management_0\";i:1;s:23:\"menu_user_distribute_53\";i:1;s:23:\"menu_user_distribute_56\";i:1;s:23:\"menu_user_distribute_55\";i:1;s:23:\"menu_user_distribute_61\";i:1;s:23:\"menu_user_distribute_60\";i:1;s:23:\"menu_user_distribute_62\";i:1;s:20:\"menu_company_auth_16\";i:0;s:20:\"menu_company_auth_15\";i:0;s:20:\"menu_company_auth_14\";i:0;s:20:\"menu_company_auth_13\";i:0;s:20:\"menu_company_auth_12\";i:0;s:20:\"menu_company_auth_11\";i:0;s:20:\"menu_company_auth_10\";i:0;s:22:\"menu_inner_user_vote_1\";i:0;s:22:\"menu_inner_user_vote_0\";i:0;s:22:\"menu_inner_user_vote_5\";i:0;s:22:\"menu_inner_user_vote_4\";i:0;s:22:\"menu_inner_user_vote_3\";i:0;s:22:\"menu_inner_user_vote_2\";i:0;s:22:\"menu_inner_user_vote_9\";i:0;s:22:\"menu_inner_user_vote_8\";i:0;s:22:\"menu_inner_user_vote_7\";i:0;s:22:\"menu_inner_user_vote_6\";i:0;s:23:\"menu_user_distribute_72\";i:0;s:23:\"menu_user_distribute_71\";i:0;s:23:\"menu_user_distribute_74\";i:0;s:23:\"menu_user_distribute_73\";i:0;s:23:\"menu_user_distribute_70\";i:1;s:23:\"menu_user_distribute_79\";i:0;s:23:\"menu_user_distribute_75\";i:0;s:23:\"menu_user_distribute_78\";i:0;s:23:\"menu_user_distribute_77\";i:0;s:20:\"menu_fangke_manage_0\";i:0;s:25:\"menu_payuser_mamagement_8\";i:1;s:25:\"menu_payuser_mamagement_7\";i:1;s:25:\"menu_payuser_mamagement_9\";i:1;s:25:\"menu_payuser_mamagement_3\";i:1;s:25:\"menu_payuser_mamagement_6\";i:1;s:25:\"menu_payuser_mamagement_5\";i:1;s:25:\"menu_payuser_mamagement_0\";i:1;s:25:\"menu_payuser_mamagement_2\";i:1;s:25:\"menu_payuser_mamagement_1\";i:1;s:23:\"menu_agent_management_9\";i:1;s:23:\"menu_agent_management_8\";i:1;s:23:\"menu_agent_management_7\";i:1;s:23:\"menu_agent_management_6\";i:0;s:23:\"menu_agent_management_5\";i:0;s:23:\"menu_agent_management_4\";i:1;s:23:\"menu_agent_management_3\";i:1;s:23:\"menu_agent_management_2\";i:1;s:20:\"menu_report_center_0\";i:0;s:26:\"menu_finance_management_50\";i:0;s:20:\"menu_report_center_1\";i:0;s:26:\"menu_finance_management_51\";i:0;s:20:\"menu_report_center_2\";i:0;s:20:\"menu_report_center_3\";i:0;s:25:\"menu_close_dynamicToken_0\";i:1;s:25:\"menu_close_dynamicToken_1\";i:0;s:25:\"menu_close_dynamicToken_2\";i:0;s:25:\"menu_close_dynamicToken_3\";i:0;s:25:\"menu_close_dynamicToken_4\";i:0;s:14:\"menu_wailian_0\";i:0;s:25:\"menu_close_dynamicToken_5\";i:0;s:23:\"menu_agent_management_1\";i:1;s:25:\"menu_close_dynamicToken_6\";i:0;s:23:\"menu_agent_management_0\";i:1;s:25:\"menu_close_dynamicToken_7\";i:0;s:25:\"menu_close_dynamicToken_8\";i:0;s:25:\"menu_close_dynamicToken_9\";i:0;s:14:\"menu_sitemap_7\";i:0;s:14:\"menu_sitemap_6\";i:0;s:14:\"menu_sitemap_5\";i:0;s:14:\"menu_sitemap_4\";i:0;s:14:\"menu_sitemap_3\";i:0;s:14:\"menu_sitemap_2\";i:0;s:14:\"menu_sitemap_1\";i:0;s:14:\"menu_sitemap_0\";i:0;s:26:\"menu_finance_management_34\";i:0;s:24:\"menu_agent_management_19\";i:1;s:26:\"menu_finance_management_35\";i:0;s:24:\"menu_agent_management_18\";i:1;s:26:\"menu_finance_management_36\";i:0;s:24:\"menu_agent_management_17\";i:1;s:26:\"menu_finance_management_37\";i:0;s:26:\"menu_finance_management_38\";i:0;s:24:\"menu_coupon_management_0\";i:0;s:26:\"menu_finance_management_39\";i:0;s:24:\"menu_coupon_management_2\";i:0;s:24:\"menu_coupon_management_1\";i:0;s:24:\"menu_agent_management_12\";i:1;s:24:\"menu_agent_management_11\";i:1;s:24:\"menu_agent_management_16\";i:1;s:26:\"menu_finance_management_30\";i:0;s:24:\"menu_agent_management_15\";i:1;s:26:\"menu_finance_management_31\";i:0;s:24:\"menu_agent_management_14\";i:1;s:26:\"menu_finance_management_32\";i:0;s:24:\"menu_agent_management_13\";i:1;s:26:\"menu_finance_management_33\";i:0;s:24:\"menu_coupon_management_4\";i:0;s:24:\"menu_coupon_management_3\";i:0;s:24:\"menu_coupon_management_6\";i:0;s:24:\"menu_coupon_management_5\";i:0;s:24:\"menu_coupon_management_7\";i:0;s:26:\"menu_finance_management_45\";i:0;s:26:\"menu_finance_management_46\";i:0;s:26:\"menu_finance_management_47\";i:0;s:26:\"menu_finance_management_48\";i:0;s:26:\"menu_finance_management_49\";i:0;s:24:\"menu_agent_management_23\";i:0;s:24:\"menu_agent_management_22\";i:1;s:24:\"menu_agent_management_21\";i:1;s:24:\"menu_agent_management_20\";i:1;s:26:\"menu_finance_management_40\";i:0;s:26:\"menu_finance_management_41\";i:0;s:24:\"menu_agent_management_26\";i:0;s:26:\"menu_finance_management_42\";i:0;s:24:\"menu_agent_management_25\";i:1;s:26:\"menu_finance_management_43\";i:0;s:24:\"menu_agent_management_24\";i:1;s:26:\"menu_finance_management_44\";i:0;s:26:\"menu_finance_management_12\";i:0;s:26:\"menu_finance_management_13\";i:0;s:26:\"menu_finance_management_14\";i:0;s:26:\"menu_finance_management_15\";i:0;s:26:\"menu_finance_management_16\";i:0;s:26:\"menu_finance_management_17\";i:0;s:26:\"menu_finance_management_18\";i:0;s:26:\"menu_finance_management_19\";i:0;s:26:\"menu_finance_management_10\";i:0;s:26:\"menu_finance_management_11\";i:0;s:22:\"menu_user_distribute_4\";i:0;s:21:\"menu_marketing_data_1\";i:0;s:22:\"menu_user_distribute_5\";i:0;s:21:\"menu_marketing_data_0\";i:0;s:22:\"menu_user_distribute_2\";i:0;s:21:\"menu_marketing_data_3\";i:0;s:22:\"menu_user_distribute_3\";i:0;s:21:\"menu_marketing_data_2\";i:0;s:22:\"menu_user_distribute_8\";i:0;s:22:\"menu_user_distribute_9\";i:0;s:22:\"menu_user_distribute_6\";i:0;s:22:\"menu_user_distribute_7\";i:0;s:21:\"menu_marketing_data_9\";i:0;s:21:\"menu_marketing_data_8\";i:0;s:22:\"menu_user_distribute_0\";i:0;s:21:\"menu_marketing_data_5\";i:0;s:22:\"menu_user_distribute_1\";i:0;s:21:\"menu_marketing_data_4\";i:0;s:21:\"menu_marketing_data_7\";i:0;s:21:\"menu_marketing_data_6\";i:0;s:26:\"menu_finance_management_23\";i:0;s:27:\"menu_finance_management2_17\";i:0;s:26:\"menu_finance_management_24\";i:0;s:27:\"menu_finance_management2_16\";i:0;s:26:\"menu_finance_management_25\";i:0;s:27:\"menu_finance_management2_15\";i:0;s:26:\"menu_finance_management_26\";i:0;s:27:\"menu_finance_management2_14\";i:0;s:26:\"menu_finance_management_27\";i:0;s:26:\"menu_finance_management_28\";i:0;s:26:\"menu_finance_management_29\";i:0;s:27:\"menu_finance_management2_19\";i:0;s:27:\"menu_finance_management2_18\";i:0;s:27:\"menu_finance_management2_13\";i:0;s:26:\"menu_finance_management_20\";i:0;s:27:\"menu_finance_management2_12\";i:0;s:26:\"menu_finance_management_21\";i:0;s:27:\"menu_finance_management2_11\";i:0;s:26:\"menu_finance_management_22\";i:0;s:26:\"menu_payuser_mamagement_11\";i:1;s:26:\"menu_payuser_mamagement_10\";i:1;s:25:\"menu_lottery_management_0\";i:0;s:25:\"menu_lottery_management_1\";i:0;s:25:\"menu_lottery_management_2\";i:0;s:25:\"menu_lottery_management_3\";i:0;s:25:\"menu_lottery_management_4\";i:0;s:25:\"menu_lottery_management_5\";i:0;s:25:\"menu_finance_management_9\";i:0;s:25:\"menu_finance_management_7\";i:0;s:25:\"menu_finance_management_8\";i:0;s:26:\"menu_payuser_mamagement_19\";i:1;s:25:\"menu_lottery_management_6\";i:0;s:26:\"menu_payuser_mamagement_18\";i:1;s:25:\"menu_lottery_management_7\";i:0;s:26:\"menu_payuser_mamagement_17\";i:1;s:25:\"menu_lottery_management_8\";i:0;s:26:\"menu_payuser_mamagement_16\";i:1;s:25:\"menu_lottery_management_9\";i:0;s:17:\"menu_recom_code_1\";i:0;s:17:\"menu_recom_code_2\";i:0;s:17:\"menu_recom_code_3\";i:0;s:17:\"menu_recom_code_4\";i:0;s:17:\"menu_recom_code_6\";i:0;s:17:\"menu_recom_code_7\";i:0;s:17:\"menu_recom_code_8\";i:0;s:25:\"menu_finance_management_1\";i:0;s:25:\"menu_finance_management_2\";i:0;s:25:\"menu_finance_management_0\";i:0;s:25:\"menu_finance_management_5\";i:0;s:25:\"menu_finance_management_6\";i:0;s:25:\"menu_finance_management_3\";i:0;s:25:\"menu_finance_management_4\";i:0;s:17:\"menu_recom_code_0\";i:0;s:26:\"menu_payuser_mamagement_20\";i:0;s:24:\"menu_helpcenter_manage_0\";i:0;s:18:\"menu_cps_manage_10\";i:0;s:20:\"menu_admin_member_12\";i:0;s:20:\"menu_admin_member_13\";i:1;s:20:\"menu_admin_member_10\";i:0;s:20:\"menu_admin_member_11\";i:0;s:20:\"menu_admin_member_16\";i:0;s:20:\"menu_admin_member_17\";i:0;s:20:\"menu_admin_member_14\";i:0;s:20:\"menu_admin_member_15\";i:0;s:16:\"menu_admin_log_7\";i:0;s:16:\"menu_admin_log_6\";i:0;s:16:\"menu_admin_log_0\";i:0;s:16:\"menu_admin_log_2\";i:0;s:18:\"menu_user_trans_10\";i:0;s:16:\"menu_admin_log_1\";i:0;s:18:\"menu_user_trans_11\";i:0;s:18:\"menu_user_trans_12\";i:0;s:18:\"menu_user_trans_13\";i:0;s:18:\"menu_user_trans_14\";i:0;s:18:\"menu_user_trans_15\";i:0;s:18:\"menu_user_trans_16\";i:0;s:24:\"menu_helpcenter_manage_5\";i:0;s:24:\"menu_helpcenter_manage_6\";i:0;s:24:\"menu_helpcenter_manage_1\";i:0;s:24:\"menu_helpcenter_manage_2\";i:0;s:24:\"menu_helpcenter_manage_3\";i:0;s:24:\"menu_helpcenter_manage_4\";i:0;s:30:\"menu_recharge_act_management_9\";i:0;s:30:\"menu_recharge_act_management_7\";i:1;s:30:\"menu_recharge_act_management_8\";i:1;s:17:\"menu_reg_member_0\";i:1;s:17:\"menu_reg_member_1\";i:1;s:17:\"menu_reg_member_8\";i:1;s:17:\"menu_reg_member_9\";i:1;s:17:\"menu_reg_member_6\";i:0;s:17:\"menu_reg_member_7\";i:1;s:17:\"menu_reg_member_4\";i:1;s:17:\"menu_reg_member_5\";i:0;s:17:\"menu_reg_member_2\";i:1;s:17:\"menu_reg_member_3\";i:1;s:19:\"menu_zcb_position_0\";i:0;s:24:\"menu_freeaccount_token_0\";i:0;s:21:\"menu_zhaopin_manage_0\";i:0;s:21:\"menu_zhaopin_manage_1\";i:0;s:21:\"menu_zhaopin_manage_2\";i:0;s:21:\"menu_zhaopin_manage_3\";i:0;s:22:\"menu_download_manage_4\";i:0;s:22:\"menu_download_manage_3\";i:0;s:22:\"menu_download_manage_0\";i:0;s:22:\"menu_download_manage_2\";i:0;s:22:\"menu_download_manage_1\";i:0;s:30:\"menu_recharge_act_management_1\";i:1;s:30:\"menu_recharge_act_management_2\";i:1;s:30:\"menu_recharge_act_management_0\";i:1;s:30:\"menu_recharge_act_management_5\";i:1;s:30:\"menu_recharge_act_management_6\";i:1;s:30:\"menu_recharge_act_management_3\";i:1;s:30:\"menu_recharge_act_management_4\";i:1;s:18:\"menu_case_manage_1\";i:0;s:18:\"menu_case_manage_0\";i:0;s:18:\"menu_case_manage_6\";i:0;s:18:\"menu_case_manage_5\";i:0;s:18:\"menu_case_manage_4\";i:0;s:18:\"menu_case_manage_3\";i:0;s:27:\"menu_gvoucher_management_15\";i:0;s:27:\"menu_gvoucher_management_12\";i:0;s:27:\"menu_finance_management2_20\";i:0;s:27:\"menu_gvoucher_management_11\";i:0;s:27:\"menu_gvoucher_management_14\";i:0;s:27:\"menu_gvoucher_management_13\";i:0;s:27:\"menu_finance_management2_24\";i:0;s:27:\"menu_finance_management2_23\";i:0;s:27:\"menu_gvoucher_management_10\";i:0;s:27:\"menu_finance_management2_22\";i:0;s:27:\"menu_finance_management2_21\";i:0;s:22:\"menu_marketing_data_10\";i:0;s:21:\"menu_modify_account_1\";i:0;s:21:\"menu_modify_account_0\";i:0;s:15:\"menu_freecall_5\";i:0;s:12:\"menu_email_0\";i:0;s:12:\"menu_email_1\";i:0;s:12:\"menu_email_2\";i:0;s:12:\"menu_email_3\";i:0;s:12:\"menu_email_4\";i:0;s:12:\"menu_email_5\";i:0;s:12:\"menu_email_6\";i:0;s:23:\"menu_guwen_management_3\";i:0;s:23:\"menu_guwen_management_4\";i:0;s:23:\"menu_guwen_management_5\";i:0;s:23:\"menu_guwen_management_6\";i:0;s:23:\"menu_guwen_management_0\";i:0;s:23:\"menu_guwen_management_1\";i:0;s:23:\"menu_guwen_management_2\";i:0;s:23:\"menu_guwen_management_7\";i:0;s:23:\"menu_guwen_management_8\";i:0;s:23:\"menu_guwen_management_9\";i:0;s:30:\"menu_new_finance_management_41\";i:0;s:30:\"menu_new_finance_management_40\";i:0;s:15:\"menu_freecall_0\";i:0;s:30:\"menu_new_finance_management_43\";i:0;s:30:\"menu_new_finance_management_42\";i:0;s:15:\"menu_freecall_3\";i:0;s:15:\"menu_freecall_4\";i:0;s:15:\"menu_freecall_1\";i:0;s:15:\"menu_freecall_2\";i:0;s:30:\"menu_new_finance_management_48\";i:0;s:30:\"menu_new_finance_management_45\";i:0;s:30:\"menu_new_finance_management_44\";i:0;s:30:\"menu_new_finance_management_47\";i:0;s:30:\"menu_new_finance_management_46\";i:0;}";
        Object o = PHPSerializer.unserialize(content.getBytes());
        System.out.println(o);

        byte[] serialize = PHPSerializer.serialize(o);
        String s = new String(serialize);
        System.out.println(s);
    }
}
