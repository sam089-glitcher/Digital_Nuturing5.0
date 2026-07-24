import "./App.css";

import BookDetails from "./BookDetails";
import BlogDetails from "./BlogDetails";
import CourseDetails from "./CourseDetails";

function App() {

    const showBooks = true;
    const showBlogs = true;
    const showCourses = true;

    return (

        <div className="container">

            {

                showCourses &&

                <div className="box">

                    <CourseDetails />

                </div>

            }

            {

                showBooks ?

                <div className="box">

                    <BookDetails />

                </div>

                : null

            }

            {

                showBlogs ?

                <div className="box">

                    <BlogDetails />

                </div>

                : null

            }

        </div>

    );

}

export default App;