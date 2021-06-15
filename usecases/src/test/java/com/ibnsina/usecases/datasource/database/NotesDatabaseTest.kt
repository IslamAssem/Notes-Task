package com.ibnsina.usecases.datasource.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.ibnsina.entites.note.Note
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class NotesDatabaseTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var notesDatabase: NotesDatabase


    @Before
    fun createInMemoryDatabase() {
        notesDatabase = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.systemContext,
            NotesDatabase::class.java).allowMainThreadQueries().build()
    }

    @After
    fun closeDatabase() {
        notesDatabase.close()
    }

    //<editor-fold desc="Save Bufferoos">
    @Test
    fun `saveNote() return id`() {
        val note = Note(1,"title","body","15/06/2021","15/06/2021")

        val id = notesDatabase.notesDao.saveNote(note)
        assertEquals(id,note.id)
    }
    //<editor-fold desc="Save Bufferoos">
    @Test
    fun `getNote() return savedNote`() {
        val note = Note(1,"title","body","15/06/2021","15/06/2021")

        val id = notesDatabase.notesDao.saveNote(note)
        assertEquals(id,note.id)
        val notes = notesDatabase.notesDao.getAllNotes()
        runBlockingTest {
            assertEquals(notes.count(),5)
            assertTrue(notes.first().contains(note))
        }
    }
}