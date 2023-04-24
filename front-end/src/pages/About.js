import React from 'react'
import { Base } from '../components/Base'
import userContext from '../context/userContext'

 const About = () => {
  return (
    <userContext.Consumer>{
      (user)=>(
        <Base>
        <h1>This is About {user.firstName}</h1>
        <p>We are building blog website</p>
        </Base>
      )
      }
    </userContext.Consumer>
  );
};
export default About;
