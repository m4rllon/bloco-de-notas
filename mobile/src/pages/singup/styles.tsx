import { TouchableOpacity } from "react-native";
import styled from "styled-components/native";

export const HeaderContainer = styled.View`
    display: flex;
    gap: 8px;
`

export const BackButton = styled(TouchableOpacity)`
    align-self: flex-start;
    margin-bottom: 4px;
    padding: 8px;
    border-radius: 16px;
    background-color: ${ props => props.theme.colors.paper3};
`

export const HeaderSubText = styled.Text`
    text-transform: uppercase;
    color: ${ props => props.theme.colors.clay};
    font-size: 14px;
    font-family: ${ props => props.theme.fonts.body.semibold};
`

export const HeaderText = styled.Text`
    color: ${ props => props.theme.colors.ink};
    font-size: 40px;
    font-family: ${ props => props.theme.fonts.display.bold };
    line-height: 42px;
`

export const FormContainer = styled.View`
    display: flex;
    gap: 16px;
`

export const LoginText = styled.Text`
    flex-shrink: 1;
    font-size: 14px;
    color: ${ props => props.theme.colors.ink};
    font-family: ${ props => props.theme.fonts.body.regular};
    align-self: center;
`

export const CtaLoginText = styled.Text`
    flex-shrink: 1;
    font-size: 14px;
    color: ${ props => props.theme.colors.clay};
    font-family: ${ props => props.theme.fonts.display.bold};
    align-self: center;
    text-decoration: underline;
`