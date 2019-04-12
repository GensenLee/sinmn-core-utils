package com.sinmn.core.utils.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.sinmn.core.utils.util.LongUtil;

public class DateConvert implements Converter<String, Date> {

	@Override
    public Date convert(String stringDate) {
		
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
        	simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	try{
        		return simpleDateFormat.parse(stringDate);
        	}catch(ParseException ee){
        		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		stringDate = simpleDateFormat.format(LongUtil.toLong(stringDate));
        		try {
        		return simpleDateFormat.parse(stringDate);
        		}catch(ParseException eee){}
        	}
        }
        return null;
    }
}
