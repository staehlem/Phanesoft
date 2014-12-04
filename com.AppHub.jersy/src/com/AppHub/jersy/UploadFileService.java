package com.AppHub.jersy;

import model.LocalPictureUrlResponseWrapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
 
@Path("/UploadFileService")
public class UploadFileService {
 
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {
 
		String uploadedFileLocation = "/Users/nathananneken/Documents/AppHub/AppHub/WebContent/images/" + fileDetail.getFileName();
 
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);
 
		System.out.println("File uploaded to : " + uploadedFileLocation);
		
		String localLoc = "images/" + uploadedFileLocation.substring(uploadedFileLocation.lastIndexOf("/")+1);
 
		return Response.status(200).entity(new LocalPictureUrlResponseWrapper(localLoc)).build();
 
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteFile(LocalPictureUrlResponseWrapper pictureUrl) {
 
		String uploadedFileLocation = "/Users/nathananneken/Documents/AppHub/AppHub/WebContent/" + pictureUrl.getMessage();
 
		try{
			 
    		File file = new File(uploadedFileLocation);
 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    			return Response.ok().build();
    		}else{
    			System.out.println("Delete operation failed.");
    			return Response.status(500).build();
    		}
 
    	}catch(Exception e){
 
    		e.printStackTrace();
    		return Response.status(500).build();
 
    	} 
	}
 
	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
		String uploadedFileLocation) {
 
		try {
			File file = new File(uploadedFileLocation);
			int copy = 1;
			while(file.exists()) {
				if(copy == 1) {
					int index = uploadedFileLocation.lastIndexOf(".");
					uploadedFileLocation = uploadedFileLocation.substring(0,index)+
							"("+copy+")"+uploadedFileLocation.substring(index);
				}
				else {
					int indexOfFirstPar = uploadedFileLocation.lastIndexOf("(");
					int indexOfSecondPar = uploadedFileLocation.lastIndexOf(")");
					uploadedFileLocation = uploadedFileLocation.substring(0,indexOfFirstPar+1)+
							copy+uploadedFileLocation.substring(indexOfSecondPar);
				}
				file = new File(uploadedFileLocation);
				copy++;
			}
			
			OutputStream out = new FileOutputStream(file);
			int read = 0;
			byte[] bytes = new byte[1024];
 
			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
 
			e.printStackTrace();
		}
 
	}
 
}