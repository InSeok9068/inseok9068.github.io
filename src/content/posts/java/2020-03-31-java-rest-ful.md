---
title: '[Java] REST API GET,POST 통신 예제'
categories:
  - Java
tags:
  - restful
---

```java
public String httpRestGetSend(String restURL, String queryParam, String methodType){
	String result = null;

	HttpURLConnection conn = null;
	BufferedReader in = null;

	try {
		URL url = new URL(restURL + queryParam);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(methodType);
		conn.setConnectTimeout(1000 * 5);

		int responseCode = conn.getResponseCode();

				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
				}

				result = response.toString();

	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		if(in != null) { try{ in.close(); }catch(Exception e) {} };
		if(conn != null) { try{ conn.disconnect(); }catch(Exception e) {} }
	}

	return result;
}

public String httpRestPostSend(String restURL, String queryParam, String methodType, Properties jsonParam) throws Exception{
	String result = null;

	HttpURLConnection conn = null;
	OutputStream os = null;
	InputStream in = null;

	try {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(jsonParam);

		URL url = new URL(restURL + queryParam);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setConnectTimeout(1000 * 5);
		conn.setRequestProperty("Content-Type", "application/json");

		os = conn.getOutputStream();
		os.write(json.getBytes("UTF-8"));

		in = new BufferedInputStream(conn.getInputStream());
		result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");

	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		if(os != null) { try{ os.close(); }catch(Exception e) {} }
		if(in != null) { try{ in.close(); }catch(Exception e) {} };
		if(conn != null) { try{ conn.disconnect(); }catch(Exception e) {} }
	}

	return result;
}

public String encodeString(Properties params) {
	StringBuffer sb = new StringBuffer(256);
	Enumeration names = params.propertyNames();

	while (names.hasMoreElements()) {
		String name = (String) names.nextElement();
		String value = params.getProperty(name);
		sb.append(URLEncoder.encode(name) + "=" + URLEncoder.encode(value));

		if (names.hasMoreElements())
			sb.append("&");
	}
	return sb.toString();
}
```
