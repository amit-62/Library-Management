package com.amit.LibManagement.dto;

import com.amit.LibManagement.model.Author;
import com.amit.LibManagement.model.Book;
import com.amit.LibManagement.model.BookType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookRequest {

    @NotBlank(message = "book title should be not blank")
    private String bookTitle;

    @NotBlank(message = "book number should be not blank")
    private String bookNo;

    @NotNull(message = "book type should be not blank")
    private BookType bookType;

    @Positive(message = "book amount should be not blank")
    private Integer securityAmount;

    @NotBlank(message = "author name should be not blank")
    private String authorName;

    @NotBlank(message = "author email should be not blank")
    private String authorEmail;

    public Author toAuthor(){
        return Author.builder().
                email(this.authorEmail).
                name(this.authorName).
                build();
    }

    public Book toBook(){
        return Book.builder().
                title(this.bookTitle).
                bookNo(this.bookNo).
                bookType(this.bookType).
                securityAmount(this.securityAmount).
                build();
    }
}
