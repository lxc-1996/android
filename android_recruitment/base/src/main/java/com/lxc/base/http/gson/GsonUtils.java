package com.lxc.base.http.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.lxc.base.bean.ResponseBody;
import com.lxc.base.http.gson.adapter.DoubleTypeAdapter;
import com.lxc.base.http.gson.adapter.FloatTypeAdapter;
import com.lxc.base.http.gson.adapter.IntegerTypeAdapter;
import com.lxc.base.http.gson.adapter.LongTypeAdapter;
import com.lxc.base.http.gson.adapter.StringTypeAdapter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Gson 解析工具类
 *
 * @author lxc
 */
public class GsonUtils {
	
	public static Gson buildGson(){
			Gson  gson = new GsonBuilder()
					.registerTypeAdapter(Integer.class, new IntegerTypeAdapter())
					.registerTypeAdapter(int.class, new IntegerTypeAdapter())
					.registerTypeAdapter(Double.class, new DoubleTypeAdapter())
					.registerTypeAdapter(double.class, new DoubleTypeAdapter())
					.registerTypeAdapter(Long.class, new LongTypeAdapter())
					.registerTypeAdapter(long.class, new LongTypeAdapter())
					.registerTypeAdapter(Float.class, new FloatTypeAdapter())
					.registerTypeAdapter(float.class, new FloatTypeAdapter())
					.registerTypeAdapter(String.class,new StringTypeAdapter())
					.create();
			return gson;
	}
	
	public static <T> ResponseBody<T> fromJsonObject(String reader, Class<T> clazz) {
		Type type = new ParameterizedTypeImpl(ResponseBody.class,
				new Class[]{clazz});
		return buildGson().fromJson(reader, type);
	}

	public static <T> T fromJson(String reader, Class<T> clazz) {
		return buildGson().fromJson(reader, clazz);
	}

	public static <T> ResponseBody<List<T>> fromJsonArray(String reader, Class<T> clazz) {
		// 生成List<T> 中的 List<T>
		Type listType = new ParameterizedTypeImpl(List.class,
				new Class[]{clazz});
		// 根据List<T>生成完整的ResponseBody<List<T>>
		Type type = new ParameterizedTypeImpl(ResponseBody.class,
				new Type[]{listType});
		return buildGson().fromJson(reader, type);
	}

	public static <T> List<T> fromJsonArray(JsonArray reader, Class<T> clazz) {
		Type type = new ParameterizedTypeImpl(List.class,
				new Class[]{clazz});
		return buildGson().fromJson(reader, type);
	}
	
	public static class ParameterizedTypeImpl implements ParameterizedType {
		private final Class raw;
		private final Type[] args;
		public ParameterizedTypeImpl(Class raw, Type[] args) {
			this.raw = raw;
			this.args = args != null ? args : new Type[0];
		}
		@Override
		public Type[] getActualTypeArguments() {
			return args;
		}
		@Override
		public Type getRawType() {
			return raw;
		}
		@Override
		public Type getOwnerType() {return null;}
	}
}
