package com.dkim.springproj.springproj.main.utility;

import java.util.List;

public class Pagination<T> {
  private List<T> items;
  private int pageSize;
  private int currentPage;

  public Pagination(List<T> items, int pageSize) {
    this.items = items;
    this.pageSize = pageSize;
    this.currentPage = 1;
  }

  public List<T> getCurrentPageItems() {
    int startIndex = (currentPage - 1) * pageSize;
    int endIndex = Math.min(startIndex + pageSize, items.size());
    return items.subList(startIndex, endIndex);
  }

  public int getTotalPages() {
    return (int) Math.ceil((double) items.size() / pageSize);
  }

  public void setCurrentPage(int page) {
    if (page < 1 || page > getTotalPages()) {
      return;
    }
    this.currentPage = page;
  }

  public void nextPage() {
    if (currentPage < getTotalPages()) {
      currentPage++;
    }
  }

  public void previousPage() {
    if (currentPage > 1) {
      currentPage--;
    }
  }
}
