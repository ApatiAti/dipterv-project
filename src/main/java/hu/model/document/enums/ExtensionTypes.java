package hu.model.document.enums;

public enum ExtensionTypes {
	PDF("pdf"),
	WORD_DOC("doc", "docx"),
	PICTURE("bmp", "jpg", "png"),
	VIDEO("mpeg", "mp4"),
	ES3("es3");
	
	private final Object[] extensions;

	private ExtensionTypes(String... extensions) {
		this.extensions = extensions;
	}

	public static ExtensionTypes fromExtensionValue(String extensionValue){
		for (ExtensionTypes extensionTypes : ExtensionTypes.values()) {
			for (Object extension : extensionTypes.extensions) {
				if(((String)extension).equals(extensionValue)){
					return extensionTypes;
				}
			}
		}
		
		throw new RuntimeException("Not implemented.");
	}
	
	public Object[] getExtensions() {
		return extensions;
	}

	
}
