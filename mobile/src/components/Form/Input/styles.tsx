import styled from "styled-components/native";
import { theme } from "../../../theme";

export const Container = styled.View`
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: start;
    gap: 4px;
`

export const Label = styled.Text`
    color: ${({theme}) => theme.colors.inkSoft};
    font-family: ${ ({theme}) => theme.fonts.body.regular};
    font-size: 14px;
`

export const InputContainer = styled.View`
    width: 100%;
    height: 58px;
    
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    
    padding: 4px 14px;
    border-radius: 16px;
    
    background-color: ${({theme}) => theme.colors.paper3};
`

export const CustomInput = styled.TextInput.attrs({
    placeholderTextColor: theme.colors.inkSoft
})`
    flex: 1;
    font-size: 16px;
    color: ${({theme}) => theme.colors.inkSoft};
`
