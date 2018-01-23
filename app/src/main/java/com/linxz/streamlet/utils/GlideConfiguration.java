package com.linxz.streamlet.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

/**
 * 描述：解决Glide加载图片出现浅绿色问题，清单要配置.
 * @author Linxz
 * E-mail:lin_xiao_zhang@163.com
 * @ddate 2018年01月22日  9:24
 * @version V1.0
 * <meta-data
 * android:name="你的包名.GlideConfiguration"
 * android:value="GlideModule"/>
 */

public class GlideConfiguration implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        glideBuilder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
