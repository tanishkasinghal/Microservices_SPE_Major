import React from 'react'
import NavBar from './NavBar'

export const Base = ({children}) => {
  return (
    <div className="container-fluid p-0 m-0">
       <NavBar/>
        {children}
    </div>
  )
}
