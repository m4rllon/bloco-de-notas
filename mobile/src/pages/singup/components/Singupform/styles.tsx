import styled from "styled-components/native"

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