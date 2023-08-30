@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <li>
        @for(var user: page.getUsers())
            <ul>${user.toString()}</ul>
        @endfor
    </li>
`
)
