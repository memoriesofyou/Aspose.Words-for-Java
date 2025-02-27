package com.aspose.words.examples.programming_documents.document;

import com.aspose.words.BreakType;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ImportFormatMode;
import com.aspose.words.ImportFormatOptions;
import com.aspose.words.Node;
import com.aspose.words.NodeImporter;
import com.aspose.words.Paragraph;
import com.aspose.words.ParagraphCollection;
import com.aspose.words.examples.Utils;

public class WorkingWithImportFormatOptions {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String dataDir = Utils.getDataDir(WorkingWithImportFormatOptions.class);
		SmartStyleBehavior(dataDir);
		KeepSourceNumbering(dataDir);
		IgnoreTextBoxes(dataDir);
	}

	public static void SmartStyleBehavior(String dataDir) throws Exception
    {
        // ExStart:SmartStyleBehavior
		Document srcDoc = new Document(dataDir + "source.docx");
		Document dstDoc = new Document(dataDir + "destination.docx");
		 
		DocumentBuilder builder = new DocumentBuilder(dstDoc);
		builder.moveToDocumentEnd();
		builder.insertBreak(BreakType.PAGE_BREAK);
		 
		ImportFormatOptions options = new ImportFormatOptions();
		options.setSmartStyleBehavior(true);
		builder.insertDocument(srcDoc, ImportFormatMode.USE_DESTINATION_STYLES, options);
        // ExEnd:SmartStyleBehavior
    }
	
	public static void KeepSourceNumbering(String dataDir) throws Exception
    {
        // ExStart:KeepSourceNumbering
        Document srcDoc = new Document(dataDir + "source.docx");
        Document dstDoc = new Document(dataDir + "destination.docx");

        ImportFormatOptions importFormatOptions = new ImportFormatOptions();
        // Keep source list formatting when importing numbered paragraphs.
        importFormatOptions.setKeepSourceNumbering(true);
        NodeImporter importer = new NodeImporter(srcDoc, dstDoc, ImportFormatMode.KEEP_SOURCE_FORMATTING, importFormatOptions);

        ParagraphCollection srcParas = srcDoc.getFirstSection().getBody().getParagraphs();
        
        for (Paragraph srcPara : srcParas)
        {
            Node importedNode = importer.importNode(srcPara, false);
            dstDoc.getFirstSection().getBody().appendChild(importedNode);
        }

        dstDoc.save(dataDir + "output.docx");
        // ExEnd:KeepSourceNumbering
    }

    public static void IgnoreTextBoxes(String dataDir) throws Exception
    {
        // ExStart:IgnoreTextBoxes
        Document srcDoc = new Document(dataDir + "source.docx");
        Document dstDoc = new Document(dataDir + "destination.docx");

        ImportFormatOptions importFormatOptions = new ImportFormatOptions();
        // Keep the source text boxes formatting when importing.
        importFormatOptions.setIgnoreTextBoxes(false);
        NodeImporter importer = new NodeImporter(srcDoc, dstDoc, ImportFormatMode.KEEP_SOURCE_FORMATTING, importFormatOptions);

        ParagraphCollection srcParas = srcDoc.getFirstSection().getBody().getParagraphs();
        for (Paragraph srcPara : srcParas)
        {
            Node importedNode = importer.importNode(srcPara, true);
            dstDoc.getFirstSection().getBody().appendChild(importedNode);
        }

        dstDoc.save(dataDir + "output.docx");
        // ExEnd:IgnoreTextBoxes
    }
}
