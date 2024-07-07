package fun.bonkers.util;

import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class BlobConverter {
	
	public String getImgData(byte[] byteData) {
		return Base64.getMimeEncoder().encodeToString(byteData);
	}
}
