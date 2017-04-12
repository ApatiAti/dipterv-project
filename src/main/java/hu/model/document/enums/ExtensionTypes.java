package hu.model.document.enums;

import org.springframework.util.StringUtils;

public enum ExtensionTypes {
	PDF(".pdf"),
	WORD_DOC(".doc", ".docx"),
	PICTURE(".bmp", ".jpg", ".png"),
	VIDEO(".mpeg", ".mp4"),
	ES3(".es3");
	
	private final Object[] extensions;

	private ExtensionTypes(String... extensions) {
		this.extensions = extensions;
	}

	public static ExtensionTypes fromFileName(String fileName){
		if (!StringUtils.isEmpty(fileName) && fileName.matches("(.)*\\.[\\w]{3,4}$")){
			for (ExtensionTypes extensionTypes : ExtensionTypes.values()) {
				for (Object extension : extensionTypes.extensions) {
					if(fileName.endsWith(((String)extension))){
						return extensionTypes;
					}
				}
			}
		}
		
		return null;
	}
	
	public Object[] getExtensions() {
		return extensions;
	}

	
}
