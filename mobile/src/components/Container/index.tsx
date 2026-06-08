import React from "react";
import { CustomContainer } from "./styles";

interface ContainerProps {
    children: React.JSX.Element | React.JSX.Element[];
}

export function Container({children}:ContainerProps){
    return <CustomContainer>
        {children}
    </CustomContainer>
}