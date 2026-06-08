import { TouchableOpacity } from "react-native";
import styled from "styled-components/native";

interface ButtonStyleProps{
    $type: String;
}

export const CustomButtonContainer = styled(TouchableOpacity)<ButtonStyleProps>`
    background-color: ${ props => {
        if(props.$type === "primary") return props.theme.colors.clay;
        else if(props.$type === "secundary") return props.theme.colors.purple;
        else return props.theme.colors.sage;
    }};
    width: 100%;
    padding: ${ props => props.theme.spacing.lg }px;

    display: flex;
    align-items: center;

    border-radius: ${ props => props.theme.borderRadius.lg }px;
`

export const CustomTextButton = styled.Text`
    font-family: ${ props => props.theme.fonts.display.bold };
    color: ${ props => props.theme.colors.paper };
    font-size: 18px;
`