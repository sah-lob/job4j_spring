package ru.job4j.carssale.models;
import java.util.ArrayList;
import java.util.List;

public class MemoryIndexesOnPage {

    private List<MemoryIndexesOnPage> arrayList;
    private int firstIndex;
    private int lastIndex;
    private int size;

    public MemoryIndexesOnPage() {
        arrayList = new ArrayList<>();
    }

    private MemoryIndexesOnPage(int firstIndex, int lastIndex) {
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
    }


    public void addPage(int page, int firstIndex, int lastIndex) {
        arrayList.add(new MemoryIndexesOnPage(firstIndex, lastIndex));
    }

    private int getFirstIndex() {
        return firstIndex;
    }

    public boolean containsKey(int page) {
        return arrayList.size() >= page;
    }

    public int getFirstIndexFromPage(int page) {
        int result = -1;
        if (arrayList.size() >= page) {
            result = arrayList.get(page - 1).getFirstIndex();
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
