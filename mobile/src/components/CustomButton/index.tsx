import React from "react";
import { CustomButtonContainer, CustomTextButton } from "./styles";
import { TouchableOpacityProps } from "react-native";

interface CustomButtonProps extends TouchableOpacityProps{
    type: String;
    children: React.JSX.Element | String;
}

export function CustomButton({ type, children, ...rest }:CustomButtonProps){
    return <CustomButtonContainer $type={type} {...rest}>
        <CustomTextButton>
            {children}
        </CustomTextButton>
    </CustomButtonContainer>
}