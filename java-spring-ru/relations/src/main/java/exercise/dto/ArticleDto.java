package exercise.dto;

import lombok.Data;


@Data// BEGIN
public class ArticleDto {

    private String name;

    private String body;

    private CategoryDto category;
}
// END
