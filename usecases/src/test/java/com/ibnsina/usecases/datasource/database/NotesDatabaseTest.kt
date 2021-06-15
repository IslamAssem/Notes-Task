package com.ibnsina.usecases.datasource.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.ibnsina.entites.note.Note
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
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
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun createInMemoryDatabase() {

        // provide the scope explicitly, in this example using a constructor parameter
        Dispatchers.setMain(testDispatcher)

        notesDatabase = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.systemContext,
            NotesDatabase::class.java).allowMainThreadQueries().build()
    }

    @After
    fun closeDatabase() {
        notesDatabase.close()
    }

    @Test
    fun `saveNote() return id`() {
        val note = Note(1,"title","body","15/06/2021","15/06/2021")
        testScope.runBlockingTest {
            val id = notesDatabase.notesDao.saveNote(note)
            assertEquals(id, note.id)
        }
    }
    @Test
    fun `getNote() return savedNote`() {

        testScope.runBlockingTest {
            val note = Note(1,"title","body","15/06/2021","15/06/2021")
            val id = notesDatabase.notesDao.saveNote(note)
            assertEquals(id,note.id)
            val notes = notesDatabase.notesDao.getAllNotes()
            assertTrue(notes.first().contains(note))
        }
    }
}