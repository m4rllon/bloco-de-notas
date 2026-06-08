import styled from 'styled-components/native';
import { theme } from './src/theme';
import { ThemeProvider } from 'styled-components';
import * as SplashScreen from 'expo-splash-screen';
import { SafeAreaView } from 'react-native-safe-area-context';
import { Welcome } from './src/pages/welcome';
import { Fraunces_600SemiBold, Fraunces_700Bold, useFonts } from '@expo-google-fonts/fraunces';
import { HankenGrotesk_400Regular, HankenGrotesk_500Medium, HankenGrotesk_600SemiBold, HankenGrotesk_700Bold } from '@expo-google-fonts/hanken-grotesk';
import { useEffect } from 'react';

const Container = styled.View`
  height: 100%;
  background-color: ${ props => props.theme.colors.paper};
  padding: ${ props => props.theme.spacing.xl }px;
`

SplashScreen.preventAutoHideAsync();

export default function App() {
  const [loaded] = useFonts({
    Fraunces_600SemiBold,
    Fraunces_700Bold,
    HankenGrotesk_400Regular,
    HankenGrotesk_500Medium,
    HankenGrotesk_600SemiBold,
    HankenGrotesk_700Bold,
  });

  useEffect(() => {
    if (loaded) SplashScreen.hideAsync();
  }, [loaded]);

  if (!loaded) return null;

  return (
    <SafeAreaView>
      <ThemeProvider theme={theme}>
          <Container>
            <Welcome/>
          </Container>
      </ThemeProvider>
    </SafeAreaView>
  );
}
