package de.amirrocker.testdagger2modules.base.data.store

import de.amirrocker.base.BaseTest
import de.amirrocker.testdagger2modules.base.data.cache.Cache
import io.reactivex.Maybe
import io.reactivex.functions.Predicate
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import polanski.option.Option
import polanski.option.function.Func1
import java.util.*


class MemoryReactiveStoreTest : BaseTest() {

    companion object {

        class TestObject(
            val id:String
        ) {
            override fun toString(): String {
                return "TestObject(id='$id')"
            }
        }

        fun createTestObjectList():List<TestObject> {
            return object : ArrayList<TestObject>() {
                init {
                    // TODO refactor to also test with aggregates
                    add(TestObject("ID1"))
                    add(TestObject("ID2"))
                    add(TestObject("ID3"))
                    add(TestObject("ID4"))
                }
            }
        }
    }

    /**
     * encapsulated the different Mockito calls to configure the mocks
     */
    private class ArrangeBuilder(val cache:Cache<String, TestObject>) {
        fun withCachedObject(`object`: TestObject): ArrangeBuilder {
            Mockito.`when`(cache.getSingular(`object`.id)).thenReturn(Maybe.just(`object`))
            return this
        }

        fun withCachedObjectList(objectList: List<TestObject>): ArrangeBuilder {
            Mockito.`when`(cache.getAll()).thenReturn(Maybe.just(objectList))
            return this
        }

        fun withEmptyCache(): ArrangeBuilder {
            Mockito.`when`(cache.getSingular(Mockito.anyString())).thenReturn(Maybe.empty())
            Mockito.`when`(cache.getAll()).thenReturn(Maybe.empty())
            return this
        }
    }

    @Mock
    lateinit var cache:Cache<String, TestObject>

    private lateinit var memoryReactiveStore : MemoryReactiveStore<String, TestObject>

    @Before
    fun setUp() {
        memoryReactiveStore = MemoryReactiveStore<String, TestObject>(Func1 {
            testObject: TestObject -> testObject.id
        }, cache)
    }

    @Test
    fun `None is emitted when cache is empty`() {
        ArrangeBuilder(cache).withEmptyCache()
        memoryReactiveStore.getSingular("ID1").test().assertValue(Option.none())
        memoryReactiveStore.getAll().test().assertValue(Option.none())
    }

    @Test
    fun `last stored object is emitted after subscription`() {

        val testModel = TestObject("ID1")
        ArrangeBuilder(cache).withCachedObject(testModel)
            .withCachedObjectList(Collections.singletonList(testModel))

        memoryReactiveStore.storeSingular(model = testModel)
        memoryReactiveStore.getSingular("ID1").test().assertValue(Option.ofObj(testModel))
    }

    @Test
    fun `singular stream emits when single object is stored`( ){
        val model = TestObject("ID1")
        ArrangeBuilder(cache).withEmptyCache()

        val to = memoryReactiveStore.getSingular("ID1").test()
        memoryReactiveStore.storeSingular(model)
        to.assertValueAt(0,
            Predicate<Option<TestObject>> { testObjectOption: Option<TestObject> ->
                testObjectOption == Option.ofObj(
                    model
                )
            }
        )
    }

    @Test
    fun `all stream emits when single object is stored`() {
        val list = Companion.createTestObjectList()
        ArrangeBuilder(cache).withCachedObjectList(list)

        val to = memoryReactiveStore.getAll().test()
        memoryReactiveStore.storeSingular(TestObject(""))

        Mockito.verify(cache, Mockito.times(2)).getAll()
        to.assertValueAt(1,
            Predicate<Option<List<TestObject>>> { listOption: Option<List<TestObject>> ->
                listOption == Option.ofObj(list)
            }
        )
    }

    @Test
    fun `singular stream emits when object list is stored`() {

        val model = TestObject("ID1")
        ArrangeBuilder(cache).withCachedObject(model)

        val to = memoryReactiveStore.getSingular("ID1").test()
        memoryReactiveStore.storeAll(createTestObjectList())

        to.assertValueAt(1,
            Predicate<Option<TestObject>> { testObjectOption: Option<TestObject> ->
                testObjectOption == Option.ofObj(
                    model
                )
            }
        )
    }


    @Test
    fun `all stream emits when object list is stored`() {
        val list = createTestObjectList()
        ArrangeBuilder(cache).withCachedObjectList(list)

        val to = memoryReactiveStore.getAll().test()
        memoryReactiveStore.storeAll(list)

        to.assertValueAt(1,
            Predicate<Option<List<TestObject>>> { listOption: Option<List<TestObject>> ->
                listOption == Option.ofObj(list)
            }
        )
    }

    @Test
    fun `singular stream emits when object list is replaced`() {
        val model = TestObject("ID1")
        ArrangeBuilder(cache).withCachedObject(model)

        val to = memoryReactiveStore.getSingular("ID1").test()
        memoryReactiveStore.replaceAll(createTestObjectList())

        to.assertValueAt(1,
            Predicate<Option<TestObject>> { testObjectOption: Option<TestObject> ->
                testObjectOption == Option.ofObj(
                    model
                )
            }
        )
    }


    @Test
    fun `all stream emits when object list is replaced`() {

        val list = createTestObjectList()
        ArrangeBuilder(cache).withCachedObjectList(list)

        val to = memoryReactiveStore.getAll().test()
        memoryReactiveStore.replaceAll(list)

        to.assertValueAt(1,
            Predicate<Option<List<TestObject>>> { listOption: Option<List<TestObject>> ->
                listOption == Option.ofObj(list)
            }
        )
    }

    @Test
    fun `object is stored in cache`() {

        val model = TestObject("ID1")
        ArrangeBuilder(cache).withCachedObjectList(Collections.singletonList(model))

        memoryReactiveStore.storeSingular(model)

        Mockito.verify(cache).putSingular(model)

    }

    @Test
    fun `object list is stored in cache`() {

        val list = createTestObjectList()

        memoryReactiveStore.storeAll(list)

        Mockito.verify(cache).putAll(list)
    }

    @Test
    fun `cache is cleared in replace all`() {
        val list = createTestObjectList()
        memoryReactiveStore.replaceAll(list)
        Mockito.verify(cache).clear()
    }

}