package com.example.calendarapp.data

import android.os.Parcelable
import com.example.calendarapp.screens.auth.model.UserModel
import com.example.calendarapp.screens.calendar.TaskAttachmentType
import com.example.calendarapp.screens.task.model.TaskCategoryModel
import com.example.calendarapp.screens.task.model.TaskModel
import com.example.calendarapp.screens.task.model.TaskPriorityTAG
import com.example.calendarapp.screens.task.model.TaskStatus
import com.example.calendarapp.util.ExtensionFunction.Companion.convertIntoColor
import kotlinx.parcelize.Parcelize

//current user fake data
val currentUser = UserModel(
    "101",
    "Raj",
    "Javiya",
    "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2080&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "2023-12-12",
    "2023-12-12"
)

val fakeUser1 = UserModel(
    "201",
    "Meet",
    "Javiya",
    "https://images.unsplash.com/photo-1651684215020-f7a5b6610f23?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "2023-12-12",
    "2023-12-12"
)

val fakeUser2 = UserModel(
    "202",
    "Yash",
    "Javiya",
    "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8dXNlcnxlbnwwfHwwfHx8MA%3D%3D",
    "2023-12-12",
    "2023-12-12"
)

val fakeUser3 = UserModel(
    "203",
    "Jonny",
    "Deep",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLi7UR0B7uDCnO8H9bwLZewfvNpNbAPQ0R9ckQ17lmxQ&s",
    "2023-12-12",
    "2023-12-12"
)

val fakeUser4 = UserModel(
    "204",
    "Tony",
    "Struck",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmboauKewMtt2btDu_VhAHTn8MPRFqTPa7VyMDUAF6ndhzNvxhDu3yuWpy-3oB8aLPQAA&usqp=CAU",
    "2023-12-12",
    "2023-12-12"
)

val fakeUser5 = UserModel(
    "205",
    "Virat",
    "kohli",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyvB6Tw7Azhryj2AApyib8b8WWbylZP8z9Hug5BkNZTw&s",
    "2023-12-12",
    "2023-12-12"
)

//this is for category module fake data
val categoryList = listOf(
    TaskCategoryModel(
        id = "1",
        categoryName = "Android Development",
        taskList = listOf(
            TaskModel(
                id = "2",
                title = "UI/UX Design",
                description = "Improve UI/UX design skills",
                category = "Android Development",
                attachmentType = TaskAttachmentType.IMAGE,
                taskStatus = TaskStatus.IN_PROGRESS,
                createdBy = currentUser,
                assignTo = listOf(
                    currentUser
                ),
                taskPriorityTAG = TaskPriorityTAG.HIGH,
                cratedAt = "2023-01-02",
                updatedAt = "2023-01-06",
                location = "Thiruvananthapuram, Kerala - Kowdiar",
                startDate = "2023-12-12 03:20 AM",
                endDate = "2023-12-12=3 12:20 PM"
            ),
            TaskModel(
                id = "3",
                title = "Testing",
                description = "Write unit and UI tests for Android apps",
                category = "Android Development",
                attachmentType = TaskAttachmentType.DOC,
                taskStatus = TaskStatus.COMPLETED,
                taskPriorityTAG = TaskPriorityTAG.MEDIUM,
                createdBy = currentUser,
                assignTo = listOf(
                    fakeUser1,
                    fakeUser2,
                    fakeUser3,
                    fakeUser1
                ),
                cratedAt = "2023-01-03",
                updatedAt = "2023-01-07",
                location = "Coimbatore, Tamil Nadu - Gandhipuram",
                startDate = "2023-12-12 03:20 AM",
                endDate = "2023-12-13 12:20 PM"


            ),
            TaskModel(
                id = "4",
                title = "Database Management",
                description = "Implement SQLite or Room database in Android apps",
                category = "Android Development",
                attachmentType = TaskAttachmentType.DOC,
                taskStatus = TaskStatus.COMPLETED,
                taskPriorityTAG = TaskPriorityTAG.URGENT,
                createdBy = fakeUser1,
                assignTo = listOf(
                    fakeUser1
                ),
                cratedAt = "2023-01-04",
                updatedAt = "2023-01-08",
                location = null, startDate = "2023-12-12 03:20 AM",
                endDate = null
            ),
            TaskModel(
                id = "5",
                title = "Kotlin Coroutines",
                description = "Explore and implement Kotlin coroutines for asynchronous programming",
                category = "Android Development",
                attachmentType = TaskAttachmentType.DOC,
                taskStatus = TaskStatus.ON_HOLD,
                taskPriorityTAG = TaskPriorityTAG.LOW,
                createdBy = currentUser,
                assignTo = listOf(
                    currentUser,
                    fakeUser1,
                    fakeUser2
                ),
                cratedAt = "2023-01-05",
                updatedAt = "2023-01-09",
                location = null, startDate = "2023-12-12 03:20 AM",
                endDate = null

            ),
            TaskModel(
                id = "6",
                title = "Firebase Integration",
                description = "Integrate Firebase services into Android apps",
                category = "Android Development",
                attachmentType = TaskAttachmentType.IMAGE,
                taskStatus = TaskStatus.IN_REVIEW,
                taskPriorityTAG = TaskPriorityTAG.MEDIUM,
                createdBy = currentUser,
                assignTo = listOf(
                    currentUser
                ),
                cratedAt = "2023-01-06",
                updatedAt = "2023-01-10",
                location = null,
                startDate = "2023-12-12 03:20 AM",
                endDate = null

            ),
            TaskModel(
                id = "7",
                title = "Stripe configuration",
                description = "Integrate stripe payment gateway in Calendar app",
                category = "Android Development",
                attachmentType = TaskAttachmentType.DOC,
                taskStatus = TaskStatus.ON_HOLD,
                taskPriorityTAG = TaskPriorityTAG.LOW,
                createdBy = currentUser,
                assignTo = listOf(
                    currentUser
                ),
                cratedAt = "2023-01-06",
                updatedAt = "2023-01-10",
                location = null,
                startDate = "2023-12-12 03:20 AM",
                endDate = null

            ),
        ),
        color = "#CCCCFF".convertIntoColor(),
        createdAt = "",
        updatedAt = ""
    ),
    TaskCategoryModel(
        id = "2",
        categoryName = "Flutter Development",
        taskList = listOf(
            TaskModel(
                id = "1",
                title = "Learn Flutter Basics",
                description = "Explore the fundamentals of Flutter development. You can use navigation to move between screens. Make sure to pass the shared view model instance to each composable that needs access to the shared data.",
                category = "Flutter Development",
                attachmentType = TaskAttachmentType.PDF,
                taskStatus = TaskStatus.IN_PROGRESS,
                taskPriorityTAG = TaskPriorityTAG.HIGH,
                createdBy = currentUser,
                assignTo = listOf(
                    fakeUser2,
                    fakeUser3,
                    fakeUser4
                ),
                cratedAt = "2023-01-01",
                updatedAt = "2023-01-02",
                location = null,
                startDate = "2023-12-12 03:20 AM",
                endDate = null

            ),
            TaskModel(
                id = "2",
                title = "Build a To-Do App",
                description = "Create a simple To-Do app using Flutter.",
                category = "Flutter Development",
                attachmentType = TaskAttachmentType.VIDEO,
                taskStatus = TaskStatus.COMPLETED,
                taskPriorityTAG = TaskPriorityTAG.LOW,
                createdBy = fakeUser1,
                assignTo = listOf(
                    fakeUser2,
                    fakeUser3,
                    fakeUser4
                ),
                cratedAt = "2023-02-01",
                updatedAt = "2023-02-03",
                location = null,
                startDate = "2023-12-12 03:20 AM",
                endDate = null

            ),
            TaskModel(
                id = "3",
                title = "Flutter State Management",
                description = "Explore different state management techniques in Flutter.",
                category = "Flutter Development",
                attachmentType = TaskAttachmentType.LINK,
                taskStatus = TaskStatus.IN_REVIEW,
                taskPriorityTAG = TaskPriorityTAG.MEDIUM,
                createdBy = fakeUser4,
                assignTo = listOf(
                    currentUser,
                    fakeUser2,
                    fakeUser3,
                    fakeUser4
                ),
                cratedAt = "2023-03-01",
                updatedAt = "2023-03-05",
                location = "Goa - Calangute",
                startDate = "2023-12-12 03:20 AM",
                endDate = null

            ), TaskModel(
                id = "4",
                title = "Responsive UI with Flutter",
                description = "Learn how to create responsive user interfaces with Flutter.",
                category = "Flutter Development",
                attachmentType = TaskAttachmentType.LINK,
                taskStatus = TaskStatus.ON_CANCELED,
                taskPriorityTAG = TaskPriorityTAG.URGENT,
                createdBy = currentUser,
                assignTo = listOf(
                    fakeUser5
                ),
                cratedAt = "2023-04-01",
                updatedAt = "2023-04-05",
                location = null,
                startDate = "2023-12-12 03:20 AM",
                endDate = null

            ),
            TaskModel(
                id = "5",
                title = "Flutter Testing Techniques",
                description = "Explore testing strategies for Flutter applications.",
                category = "Flutter Development",
                attachmentType = TaskAttachmentType.VIDEO,
                taskStatus = TaskStatus.IN_REVIEW,
                taskPriorityTAG = TaskPriorityTAG.LOW,
                createdBy = currentUser,
                assignTo = listOf(
                    fakeUser4,
                    fakeUser3
                ),
                cratedAt = "2023-05-01",
                updatedAt = "2023-05-07",
                location = null,
                startDate = "2023-12-12 03:20 AM",
                endDate = null

            )
        ),
        color = "#40E0D0".convertIntoColor(),
        createdAt = "",
        updatedAt = ""
    ),
    TaskCategoryModel(
        id = "3",
        categoryName = "Data Structure",
        taskList = listOf(
            TaskModel(
                id = "1",
                title = "Introduction to Data Structures",
                description = "Overview of basic data structures.",
                category = "Data Structure",
                attachmentType = TaskAttachmentType.PDF,
                taskStatus = TaskStatus.COMPLETED,
                taskPriorityTAG = TaskPriorityTAG.LOW,
                createdBy = currentUser,
                assignTo = listOf(
                    currentUser,
                    fakeUser1,
                    fakeUser4,
                    fakeUser5
                ),
                cratedAt = "2023-01-01",
                updatedAt = "2023-01-02",
                location = "Indore, Madhya Pradesh - Vijay Nagar",
                startDate = "2023-12-12 03:20 AM",
                endDate = null

            ),
            TaskModel(
                id = "2",
                title = "Arrays and Linked Lists",
                description = "Understanding arrays and linked lists.",
                category = "Data Structure",
                attachmentType = TaskAttachmentType.VIDEO,
                taskStatus = TaskStatus.COMPLETED,
                taskPriorityTAG = TaskPriorityTAG.HIGH,
                createdBy = fakeUser3,
                assignTo = listOf(
                    currentUser,
                    fakeUser2,
                    fakeUser4
                ),
                cratedAt = "2023-02-01",
                updatedAt = "2023-02-05",
                location = null,
                startDate = "2023-12-14 03:20 AM",
                endDate = "2023-12-19 01:20 AM"

            )
        ),
        color = "#FF7F50".convertIntoColor(),
        createdAt = "",
        updatedAt = ""
    ),
    TaskCategoryModel(
        id = "3",
        categoryName = "Daily Task",
        taskList = listOf(
            TaskModel(
                id = "1",
                title = "Exercise",
                description = "Yoga Practice",
                category = "Daily Task",
                attachmentType = TaskAttachmentType.VIDEO,
                taskStatus = TaskStatus.IN_PROGRESS,
                taskPriorityTAG = TaskPriorityTAG.HIGH,
                createdBy = currentUser,
                assignTo = listOf(
                    currentUser,
                ),
                cratedAt = "2023-01-01",
                updatedAt = "2023-01-02",
                location = "My Place",
                startDate = "2023-12-12 03:20 AM",
                endDate = null
            ),

            ),
        color = "#F5B041".convertIntoColor(),
        createdAt = "",
        updatedAt = ""
    ),
    TaskCategoryModel(
        id = "4",
        categoryName = "Android Jetpack",
        taskList = listOf(),
        color = "#F5B041".convertIntoColor(),
        createdAt = "",
        updatedAt = ""
    )
)


//this is for calendar module fake data
val tasksList = listOf(
    TaskModel(
        id = "1",
        title = "Learn jetpack compose",
        description = "Explore the Jetpack Compose framework for Android app development.",
        category = "Android",
        attachmentType = TaskAttachmentType.LINK,
        taskStatus = TaskStatus.COMPLETED,
        createdBy = fakeUser1,
        assignTo = listOf(
            fakeUser1
        ),
        cratedAt = "2023-01-01",
        updatedAt = "2023-01-05",
        location = "Kolkata, West Bengal - Salt Lake City",
        startDate = "2023-12-16 03:20 AM",
        endDate = null

    ),
    TaskModel(
        id = "2",
        title = "Write a Kotlin tutorial",
        description = "Create a comprehensive tutorial on Kotlin programming language.",
        category = "Kotlin",
        attachmentType = TaskAttachmentType.XLS,
        taskStatus = TaskStatus.COMPLETED,
        createdBy = fakeUser3,
        assignTo = listOf(
            currentUser,
            fakeUser2,
            fakeUser1
        ),
        cratedAt = "2023-02-01",
        updatedAt = "2023-02-10",
        location = null,
        startDate = "2023-12-18 03:20 AM",
        endDate = null

    ),
    TaskModel(
        id = "3",
        title = "Build a weather app",
        description = "Develop a weather application using a weather API.",
        category = "Flutter",
        attachmentType = TaskAttachmentType.DOC,
        taskStatus = TaskStatus.IN_PROGRESS,
        taskPriorityTAG = TaskPriorityTAG.URGENT,
        createdBy = currentUser,
        assignTo = listOf(
            currentUser
        ),
        cratedAt = "2023-03-01",
        updatedAt = "2023-03-15",
        location = null,
        startDate = "2023-12-14 03:20 AM",
        endDate = null

    ),
    TaskModel(
        id = "4",
        title = "Design a logo",
        description = "Create a unique and visually appealing logo for a new project.",
        category = "Designing",
        attachmentType = TaskAttachmentType.IMAGE,
        taskStatus = TaskStatus.IN_REVIEW,
        createdBy = currentUser,
        assignTo = listOf(
            currentUser
        ),
        cratedAt = "2023-04-01",
        updatedAt = "2023-04-10",
        location = "Pune, Maharashtra - Koregaon Park",
        startDate = "2023-12-13 03:20 AM",
        endDate = null

    ),
    TaskModel(
        id = "5",
        title = "Record a tutorial video",
        description = "Produce a video tutorial demonstrating a specific programming concept.",
        category = "Video Recoding",
        attachmentType = TaskAttachmentType.VIDEO,
        taskStatus = TaskStatus.COMPLETED,
        createdBy = fakeUser4,
        assignTo = listOf(
            fakeUser2,
            fakeUser1,
            currentUser
        ),
        cratedAt = "2023-05-01",
        updatedAt = "2023-05-15",
        location = "Kolkata, West Bengal - Salt Lake City",
        startDate = "2023-12-17 03:20 AM",
        endDate = "2023-12-15 03:20 AM"

    ),
    TaskModel(
        id = "6",
        title = "Explore Kotlin coroutines",
        description = "Learn and implement Kotlin coroutines for asynchronous programming.",
        category = "Android Advanced",
        attachmentType = TaskAttachmentType.PDF,
        taskStatus = TaskStatus.ON_CANCELED,
        createdBy = currentUser,
        assignTo = listOf(
            currentUser
        ),
        cratedAt = "2023-06-01",
        updatedAt = "2023-06-10",
        location = "Hyderabad, Telangana - Jubilee Hills",
        startDate = "2023-12-14 03:20 AM",
        endDate = null

    ),
    TaskModel(
        id = "7",
        title = "Write a blog post",
        description = "Compose a blog post on a technology-related topic of interest.",
        category = "Writing",
        attachmentType = TaskAttachmentType.LINK,
        taskStatus = TaskStatus.ON_HOLD,
        taskPriorityTAG = TaskPriorityTAG.HIGH,
        createdBy = currentUser,
        assignTo = listOf(
            currentUser
        ),
        cratedAt = "2023-07-01",
        updatedAt = "2023-07-15",
        location = "Chennai, Tamil Nadu - Adyar",
        startDate = "2023-12-13 03:20 AM",
        endDate = "2023-12-16 02:20 AM"

    ),
    TaskModel(
        id = "8",
        title = "Create a mobile app wireframe",
        description = "Design the basic layout and structure of a mobile app using wireframing tools.",
        category = "Ui/UX",
        attachmentType = TaskAttachmentType.IMAGE,
        taskStatus = TaskStatus.COMPLETED,
        taskPriorityTAG = TaskPriorityTAG.LOW,
        createdBy = currentUser,
        assignTo = listOf(
            currentUser
        ),
        cratedAt = "2023-08-01",
        updatedAt = "2023-08-10",
        location = "Bangalore, Karnataka - Koramangala",
        startDate = "2023-12-15 11:20 PM",
        endDate = null

    ),
    TaskModel(
        id = "9",
        title = "Implement user authentication",
        description = "Integrate user authentication into an existing application.",
        category = "Backend Development",
        attachmentType = TaskAttachmentType.NONE,
        taskStatus = TaskStatus.IN_PROGRESS,
        taskPriorityTAG = TaskPriorityTAG.MEDIUM,
        createdBy = fakeUser4,
        assignTo = listOf(
            fakeUser4
        ),
        cratedAt = "2023-09-01",
        updatedAt = "2023-09-15",
        location = "Delhi - Connaught Place",
        startDate = "2023-12-12 03:20 AM",
        endDate = "2023-12-13 01:20 AM"

    ),
    TaskModel(
        id = "10",
        title = "Explore machine learning basics",
        description = "Study the fundamentals of machine learning and its applications.",
        category = "AI/ML",
        attachmentType = TaskAttachmentType.LINK,
        taskStatus = TaskStatus.COMPLETED,
        createdBy = fakeUser5,
        assignTo = listOf(
            currentUser,
            fakeUser5,
            fakeUser1
        ),
        cratedAt = "2023-10-01",
        updatedAt = "2023-10-10",
        location = "Mumbai, Maharashtra - Bandra West",
        startDate = "2023-12-12 03:20 AM",
        endDate = "2023-12-15 03:20 AM"

    )
)

@Parcelize
data class ColorModel(var r: Float, var g: Float, var b: Float) : Parcelable