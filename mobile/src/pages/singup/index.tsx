import { Container } from "../../components/Container";
import { 
    BackButton, 
    HeaderContainer, 
    HeaderSubText, 
    HeaderText } from "./styles";
import { EvilIcons } from "@react-native-vector-icons/evil-icons";
import { SingUpForm } from "./components/Singupform";

export function SingUp(){
    return <Container>
        <HeaderContainer>
            <BackButton activeOpacity={0.7}>
                <EvilIcons name="chevron-left" size={30}/>
            </BackButton>
            <HeaderSubText>Leva menos de um minuto</HeaderSubText>
            <HeaderText>Criar conta</HeaderText>
        </HeaderContainer>
        <SingUpForm/>
    </Container>
}