import React, { Component } from "react";

function LoginButton(props) {
    return (
        <button onClick={props.onClick}>
            Login
        </button>
    );
}

function LogoutButton(props) {
    return (
        <button onClick={props.onClick}>
            Logout
        </button>
    );
}

function GuestGreeting() {
    return (
        <div>
            <h1>Please sign up.</h1>

            <h3>Flight Details</h3>

            <ul>
                <li>Flight : AI-202</li>
                <li>From : Delhi</li>
                <li>To : Chennai</li>
                <li>Departure : 10:30 AM</li>
            </ul>
        </div>
    );
}

function UserGreeting() {
    return (
        <div>
            <h1>Welcome back</h1>

            <h3>You can now book your tickets.</h3>

            <ul>
                <li>Flight : AI-202</li>
                <li>From : Delhi</li>
                <li>To : Chennai</li>
                <li>Status : Booking Open</li>
            </ul>
        </div>
    );
}

function Greeting(props) {

    if (props.isLoggedIn) {
        return <UserGreeting />;
    }

    return <GuestGreeting />;
}

class LoginControl extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoggedIn: false
        };
    }

    handleLoginClick = () => {
        this.setState({
            isLoggedIn: true
        });
    };

    handleLogoutClick = () => {
        this.setState({
            isLoggedIn: false
        });
    };

    render() {

        let button;

        if (this.state.isLoggedIn) {

            button = (
                <LogoutButton
                    onClick={this.handleLogoutClick}
                />
            );

        } else {

            button = (
                <LoginButton
                    onClick={this.handleLoginClick}
                />
            );

        }

        return (

            <div>

                <Greeting
                    isLoggedIn={this.state.isLoggedIn}
                />

                <br />

                {button}

            </div>

        );

    }
}

export default LoginControl;