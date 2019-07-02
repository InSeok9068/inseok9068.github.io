---
title: "Request 파라미터 HashMap으로 변환"
categories: 
  - java
tags : 
  - request
last_modified_at: 2019-07-01T13:00:00+09:00
---

``` java
public HashMap<String,Object> formatMapRequest(HttpServletRequest request) {
    HashMap<String, Object> map = new HashMap<String, Object>();
    
    Enumeration<String> enumber = request.getParameterNames();
    
    while (enumber.hasMoreElements()) {
        String key = enumber.nextElement().toString();
        String value = request.getParameter(key);

        map.put(key, value);  
    }
    
    return map;
}
```
