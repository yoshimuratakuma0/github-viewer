package domain.entities

import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class UserDetailTest {
    private val userDetail = UserDetail(
        User(
            id = 1,
            username = "test username",
            avatarUrl = "https://test.com"
        ),
        followers = 1,
        following = 2,
        updatedAt = LocalDateTime.MAX,
        createdAt = LocalDateTime.MIN
    )

    @Test
    fun displayNameWithName() {
        val target = userDetail.copy(name = "test name")
        assertEquals("test name", target.displayName)
    }

    @Test
    fun displayNameWithoutName() {
        val target = userDetail.copy(name = null)
        assertEquals("test username", target.displayName)
    }

    @Test
    fun hasCompanyWithCompany() {
        val target = userDetail.copy(company = "test company")
        assertEquals(true, target.hasCompany)
    }

    @Test
    fun hasCompanyWithoutCompany() {
        val target = userDetail.copy(company = null)
        assertEquals(false, target.hasCompany)
    }

    @Test
    fun hasEmailWithEmail() {
        val target = userDetail.copy(email = "test@gmail.com")
        assertEquals(true, target.hasEmail)
    }

    @Test
    fun hasEmailWithoutEmail() {
        val target = userDetail.copy(email = null)
        assertEquals(false, target.hasEmail)
    }

    @Test
    fun hasBioWithBio() {
        val target = userDetail.copy(bio = "test bio")
        assertEquals(true, target.hasBio)
    }

    @Test
    fun hasBioWithoutBio() {
        val target = userDetail.copy(bio = null)
        assertEquals(false, target.hasBio)
    }
}