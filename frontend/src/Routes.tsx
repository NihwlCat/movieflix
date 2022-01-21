import Navbar from "core/components/Navbar";
import { isAuthenticated } from "core/functions";
import Catalog from "pages/Catalog";
import Home from "pages/Home";
import MovieDescription from "pages/MovieDescription";
import { BrowserRouter, Switch, Route, Redirect } from "react-router-dom";

const Routes = () => (
    <BrowserRouter>
        <Navbar/>
        <Switch>
            <Route path="/" exact render={({location}) => {
                if(isAuthenticated()) {
                    return (<Redirect to={{pathname: "/movies", state: { from: location }}} />)
                }
                return <Home/> 
            }}/>
           <Route path="/movies" exact>
               <Catalog/>
           </Route>
           <Route path="/movies/:movieId">
               <MovieDescription/>
           </Route>
        </Switch>
    </BrowserRouter>
)

export default Routes;