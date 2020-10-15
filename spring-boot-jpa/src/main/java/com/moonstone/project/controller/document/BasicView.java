package com.moonstone.project.controller.document;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.TreeNode;

@Named("ttBasicView")
@ViewScoped
public class BasicView implements Serializable {
     
    private TreeNode root;
    private TreeNode rootFiles;
     
    private Document selectedDocument;
         
    @Inject
    private DocumentService service;
     
    @PostConstruct
    public void init() {
        root =  service.createDocuments();
        rootFiles = service.cerateDocumentsWithoutFolder();
    }
 
    public TreeNode getRootFiles() {
		return rootFiles;
	}

	 

	public TreeNode getRoot() {
        return root;
    }
 
    public void setService(DocumentService service) {
        this.service = service;
    }
 
    public Document getSelectedDocument() {
        return selectedDocument;
    }
 
    public void setSelectedDocument(Document selectedDocument) {
        this.selectedDocument = selectedDocument;
    }
}
