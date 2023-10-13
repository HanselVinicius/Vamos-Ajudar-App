package br.got.vamosajudar.model.ong;

import java.util.List;

public class OngResponse<T> {

    private List<T> content;
    private boolean last;
    private int totalPages;
    private Long totalElements;
    private int size;
    private boolean empty;
    private boolean first;
    private int numberOfElements;


    public List<T> getContent() {
        return content;
    }

    public boolean isLast() {
        return last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isFirst() {
        return first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    @Override
    public String toString() {
        return "OngResponse{" +
                "content=" + content +
                ", last=" + last +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", size=" + size +
                ", empty=" + empty +
                ", first=" + first +
                ", numberOfElements=" + numberOfElements +
                '}';
    }
}
