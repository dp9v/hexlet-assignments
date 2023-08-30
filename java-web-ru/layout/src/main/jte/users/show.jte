@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
    <li>
        <ul>${page.getUser().toString()}</ul>
    </li>
`
)
