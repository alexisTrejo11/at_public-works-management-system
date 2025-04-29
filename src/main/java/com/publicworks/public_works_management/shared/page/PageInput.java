package com.publicworks.public_works_management.shared.page;

public record PageInput(int pageNumber, int pageSize) {
    public static PageInput defaultPage() {
        return new PageInput(0, 10);
    }
}
