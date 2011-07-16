/**
 * Kuebiko - NoteTableModelTest.java
 * Copyright 2011 Dave Huffman (daveh303 at yahoo dot com).
 * TODO license info.
 */

package dmh.kuebiko.view;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;


import dmh.kuebiko.model.Note;
import dmh.kuebiko.test.TestHelper;
import dmh.kuebiko.view.NoteTableModel;

/**
 * Unit test class for NoteTableModel.
 * 
 * @see dmh.kuebiko.view.NoteTableModel
 * 
 * @author davehuffman
 */
public class NoteTableModelTest {
    @Test
    public void emptyModelTest() {
        NoteTableModel model = TestHelper.newNoteTableModel();

        assertNull(model.getValueAt(0, 0), "Model should be empty.");
        assertTrue(model.getRowCount() == 0, "Model should not contain any rows.");
    }

    @Test
    public void getValueAtTest() {
        Note note = new Note();
        note.setTitle("foobar");
        NoteTableModel model = TestHelper.newNoteTableModel(note);
        final Object coordVal = model.getValueAt(0, 0);
        final Object enumVal = model.getValueAt(0, NoteTableModel.Column.TITLE);

        assertNotNull(coordVal, "Model should have a value.");
        assertEquals(coordVal, note.getTitle());

        assertNotNull(enumVal, "Model should have a value.");
        assertEquals(enumVal, note.getTitle());

        assertTrue(coordVal.equals(enumVal),
                "Both getValueAt(...) methods should return the same result.");
    }
}