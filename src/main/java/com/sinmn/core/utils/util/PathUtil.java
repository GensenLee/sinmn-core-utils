package com.sinmn.core.utils.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathUtil {

	public static String join(String source,String ... args) {
		String[] tmp = source.replaceAll("\\\\", "/").split("/");
		if(args.length == 0) {
			return StringUtil.join(tmp, File.separatorChar+"");
		}
		
		List<String> liTmp = new ArrayList<String>(Arrays.asList(tmp));
		
		for(int i=0;i<args.length;i++) {
			String[] t = args[i].replaceAll("\\\\", "/").split("/");
			for(int j=0;j<t.length;j++) {
				if(t[j].equals(".")) {
					continue;
				}else if(t[j].equals("..")) {
					liTmp.remove(liTmp.size() - 1);
				}else {
					if(StringUtil.isEmpty(t[j].trim())) {
						continue;
					}
					liTmp.add(t[j]);
				}
			}
		}
		return StringUtil.join(liTmp, File.separatorChar+"");
	}
}
